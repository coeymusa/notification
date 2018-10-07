package corey.hue.notifications.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import corey.hue.notifications.brige.client.HttpClientException;
import corey.hue.notifications.brige.client.HueClient;
import corey.hue.notifications.model.Colour;
import corey.hue.notifications.model.Light;

@Service
public class LightService {

  private HueClient client = new HueClient();

  public void handleRequest(String triggerEffect, Colour colour) throws HttpClientException {
    List<Light> ligthsPreviousState = client.getLights(null);

    //useEffect
    List<Light> lightsNewState = changeLights(triggerEffect,colour,ligthsPreviousState);
    //PostChanges
    client.postLights(lightsNewState);
    //RevertChanges

  }
  
  private List<Light> changeLights(String effect, Colour colour, List<Light> lights){
    lights.forEach(light ->{
      light.getState().setOn(true);
      light.getState().setEffect(effect);
      light.getState().setHue(colour.getValue());
    });
    return lights;
  }

  public List<Light> turnLightsOn(List<Light> lights){
    lights.forEach(light ->{
      if(light.getState().isOn() == false){
        light.getState().setOn(true);
      }
    });
    return lights;
  }
}
