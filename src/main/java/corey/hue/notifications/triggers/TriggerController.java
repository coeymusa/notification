package corey.hue.notifications.triggers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import corey.hue.notifications.lights.HttpClientException;
import corey.hue.notifications.model.Trigger;

@Controller
@RequestMapping("/triggers")
public class TriggerController {

  @Autowired
  private TriggerBusinessService triggerService;
  
  @RequestMapping(method= RequestMethod.GET)
  public List<Trigger> getAllTriggers(){
    return triggerService.findAllTriggers();
  }
  
  @CrossOrigin()
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity<String> addTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    triggerService.addTrigger(trigger);
    return ResponseEntity.ok().build();
  }

  @CrossOrigin()
  @PostMapping(value = "/remove")
  public ResponseEntity<Trigger> removeTrigger(@RequestBody Trigger trigger) throws HttpClientException{
    return ResponseEntity.ok(triggerService.removeTrigger(trigger));
  }
}
