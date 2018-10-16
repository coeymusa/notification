package corey.hue.notifications.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import corey.hue.notifications.model.Light;

@RestController
@RequestMapping("/")
public class HueController {

  @Autowired
  private HueClient client;
  
  @RequestMapping(value = "/getLights", method = RequestMethod.GET)
  public List<Light> getAllLights() throws HttpClientException, JsonProcessingException{
    return client.getLights(null);
  }

}
