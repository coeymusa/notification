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

@Service
public class EmailService {

  @Value("${corey.hue.email.triggers:Shane Dawson}")
  public String trigger;

  List<Trigger> triggers = new ArrayList();
  
  @Autowired
  LightService lightService = new LightService();

  public void handleEmail(Email email) throws HttpClientException {
    populateTriggers();
    
    triggers.forEach(trig -> {
      if(email.getBody().contains(trig.getName())){
        try {
          lightService.handleRequest("colorloop", Colour.BLUE);
        } catch (HttpClientException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void populateTriggers() {
  
    //call trigger population service
    //read from file
    Trigger trigger = new Trigger();
    trigger.setColor(Colour.BLUE);
    trigger.setName("Shane Dawson");
    trigger.setEffect(Effect.COLORLOOP);
    triggers.add(trigger);
  }



}
