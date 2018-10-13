package corey.hue.notifications.email;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.gmail.model.Email;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.service.LightService;
import corey.hue.notifications.trigger.TriggerBusinessService;

@Service
public class EmailService {

  @Value("${corey.hue.email.triggers:Shane Dawson}")
  public String trigger;

  @Autowired
  private TriggerBusinessService triggerService;
  
  List<Trigger> triggers = new ArrayList();

  @Autowired
  LightService lightService = new LightService();

  public void handleEmail(Email email) throws HttpClientException {
    triggers = triggerService.findAllTriggers();

        if(email != null){
          triggers.forEach(trigger -> {
            if(email.getSubject().toLowerCase().contains((trigger.getName().toLowerCase()))){
              try {
                lightService.handleRequest(trigger.getEffect(),trigger.getColour());
              } catch (HttpClientException | InterruptedException e) {
                e.printStackTrace();
              }
            }
          });
        }
  }


}
