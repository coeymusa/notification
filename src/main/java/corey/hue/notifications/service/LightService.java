package corey.hue.notifications.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import corey.hue.notifications.brige.client.HttpClientException;
import corey.hue.notifications.brige.client.HueClient;
import corey.hue.notifications.model.Colour;
import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Light;

@Service
public class LightService {

  private HueClient client = new HueClient();
  boolean wait = true;
  public void handleRequest(Effect effect, double[] colour) throws HttpClientException, InterruptedException {
    List<Light> oldLights = client.getLights(null);
    List<Light> newLights = client.getLights(null);
    //PostChanges
    client.postLights(changeLights(effect,colour,newLights));
    //RevertChanges
    TimeUnit.MINUTES.sleep(1);
    client.postLights(oldLights);
  }
  
  private List<Light> changeLights(Effect effect, double[] colour, List<Light> lights){
    lights.forEach(light ->{
      light.getState().setOn(true);
      light.getState().setEffect(effect.getValue());
      light.getState().setXy(colour);
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
  
  public List<Light> turnLightsOff(List<Light> lights){
    lights.forEach(light ->{
      if(light.getState().isOn() == true){
        light.getState().setOn(false);
      }
    });
    return lights;
  }
}
