package corey.hue.notifications.trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.gmail.model.Email;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.trigger.TriggerBusinessService;

@Controller
@RequestMapping("/")
public class TriggerController {

  @Autowired
  private TriggerBusinessService triggerService;
  
  @CrossOrigin()
  @RequestMapping(value = "/addtrigger", method = RequestMethod.POST)
  public ResponseEntity<String> addTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    triggerService.addTrigger(trigger);
    return ResponseEntity.ok().build();
  }

  @CrossOrigin()
  @PostMapping(value = "/removeTrigger")
  public ResponseEntity<Trigger> removeTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    return ResponseEntity.ok(triggerService.removeTrigger(trigger));
  }
}
