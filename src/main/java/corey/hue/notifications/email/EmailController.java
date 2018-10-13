package corey.hue.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.gmail.model.Email;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.trigger.TriggerBusinessService;

@Controller
@RequestMapping("/")
public class EmailController {

  @Autowired
  private EmailService service;
  
  @Autowired
  private TriggerBusinessService triggerService;

  //Receives requests from a webhook. Currently using automate.io which is connected to gmail account - triggering upon receipt of new email 
  //Sending From, Subject, Body
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<String> newEmailReceived(@RequestBody Email email) throws HttpClientException{
    service.handleEmail(email);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/addtrigger", method = RequestMethod.POST)
  public ResponseEntity<String> addTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    triggerService.addTrigger(trigger);
    return ResponseEntity.ok().build();
  }

}
