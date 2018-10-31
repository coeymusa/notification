package corey.hue.notifications.trigger;

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
@RequestMapping("/trigger")
public class TriggerController {

  @Autowired
  private TriggerBusinessService triggerService;

  @RequestMapping(value = "/addtrigger", method = RequestMethod.POST)
  public ResponseEntity<String> addTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    triggerService.addTrigger(trigger);
    return ResponseEntity.ok().build();
  }

}
