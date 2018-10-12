package corey.hue.notifications.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.client.HueClient;
import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Light;

@Service
public class LightService {
  
  @Autowired
  private HueClient client;
  
  boolean wait = true;
  public void handleRequest(Effect effect, double[] colour) throws HttpClientException, InterruptedException {
    List<Light> oldLights = client.getLights(null);
    List<Light> newLights = changeLights(effect,colour,client.getLights(null));
    
    //PostChanges
    client.postLights(newLights);
    //RevertChanges
    Thread.sleep(30);
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
