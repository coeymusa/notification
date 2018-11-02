package corey.hue.notifications.lights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import corey.hue.notifications.model.Light;
import corey.hue.notifications.model.SimpleLight;

@RestController
@RequestMapping("/lights")
public class LightController {

  @Autowired
  private LightBusinessService lightService;
  

  @CrossOrigin()
  @PostMapping(value = "/")
  public ResponseEntity<Light> handleLight(@RequestBody SimpleLight light) throws HttpClientException{
    return ResponseEntity.ok(lightService.handleUILight(light));
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<SimpleLight>> getLights() throws  JsonProcessingException, HttpClientException{
    return ResponseEntity.ok(lightService.uiLights());
  }
}
