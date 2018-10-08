package corey.hue.notifications.gmail.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import corey.hue.notifications.brige.client.HttpClientException;
import corey.hue.notifications.gmail.model.Email;
import corey.hue.notifications.model.Colour;
import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.service.LightService;
import corey.hue.notifications.trigger.TriggerRepository;

@Service
public class EmailService {

  @Value("${corey.hue.email.triggers:Shane Dawson}")
  public String trigger;

  private TriggerRepository triggerRepository = new TriggerRepository();
  List<Trigger> triggers = new ArrayList();
  
  @Autowired
  LightService lightService = new LightService();

  public void handleEmail(Email email) throws HttpClientException {
    List<Trigger> triggers = populateTriggers();
    
    triggers.forEach(trigger -> {
      if(email.getBody().contains((trigger.getName().toLowerCase()))){
        try {
          lightService.handleRequest(trigger.getEffect(),trigger.getColour());
        } catch (HttpClientException | InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private  List<Trigger> populateTriggers() {
    return  triggerRepository.getTriggers();
  }



}
