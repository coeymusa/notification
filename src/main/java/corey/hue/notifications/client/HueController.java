package corey.hue.notifications.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import corey.hue.notifications.model.Light;
import corey.hue.notifications.model.SimpleLight;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.service.LightService;
import corey.hue.notifications.trigger.TriggerBusinessService;

@RestController
@RequestMapping("/")
public class HueController {

  @Autowired
  private HueClient client;
  
  @Autowired
  private LightService service;
  
  @Autowired
  private TriggerBusinessService triggerService;
//
//  
//  @RequestMapping(value = "/lights", method = RequestMethod.GET)
//  public List<Light> getAllLights() throws JsonProcessingException, HttpClientException{
//    return client.getLights(null);
//  }
  
  @RequestMapping(value = "/triggers", method= RequestMethod.GET)
  public List<Trigger> getAllTriggers(){
    return triggerService.findAllTriggers();
  }

  @CrossOrigin()
  @PostMapping(value = "/lights")
  public ResponseEntity<Light> handleLight(@RequestBody SimpleLight light) throws HttpClientException{
    return ResponseEntity.ok(service.handleUILight(light));
  }
  

  
  @RequestMapping(value = "/lights", method = RequestMethod.GET)
  public List<SimpleLight> getLights() throws  JsonProcessingException, HttpClientException{
    return service.UIFriendlyLights(client.getLights(null));
  }
}
