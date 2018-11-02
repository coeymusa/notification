package corey.hue.notifications.lights;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Light;
import corey.hue.notifications.model.SimpleLight;

@Service
public class LightBusinessService {

	@Autowired
	private HueClient client;

	private static final Logger logger = LoggerFactory.getLogger(LightBusinessService.class);
	
	boolean wait = true;
	
	public void handleRequest(Effect effect, double[] colour) throws HttpClientException, InterruptedException {
		logger.debug("Updating lights");
		List<Light> oldLights = client.getLights(null);
		List<Light> newLights = changeLights(effect,colour,client.getLights(null));

		//PostChanges
		client.postLights(newLights);
		//RevertChanges
		Thread.sleep(30);
		client.postLights(oldLights);
	}


	public List<SimpleLight> uiLights() throws HttpClientException {
		logger.debug("Getting lights for ui");
		List<Light> lights = client.getLights(null);
		List<SimpleLight> simpleLights = new ArrayList<SimpleLight>();
		lights.forEach(light -> {
			simpleLights.add(lightToSimpleLight(light));
		});
		return simpleLights;
	}

	public Light handleUILight(SimpleLight simpleLight) throws HttpClientException {
		logger.debug("Posting lights from ui");
		List<Light> lights = new ArrayList<Light>();
		Light light = UiLightToLight(simpleLight);
		lights.add(light);
		client.postLights(lights);
		return light;
	}

	public List<Light> turnLightsOn(List<Light> lights){
		logger.debug("Turning lights on");
		lights.forEach(light ->{
			if(light.getState().isOn() == false){
				light.getState().setOn(true);
			}
		});
		return lights;
	}

	public List<Light> turnLightsOff(List<Light> lights){
		logger.debug("Turning lights off");
		lights.forEach(light ->{
			if(light.getState().isOn() == true){
				light.getState().setOn(false);
			}
		});
		return lights;
	}

	private Light UiLightToLight(SimpleLight simpleLight) throws HttpClientException {
		List<Light> lights = client.getLights(String.valueOf(simpleLight.getId()));
		Light light = lights.get(0);
		light.getState().setXy(simpleLight.getXy());
		light.getState().setOn(Boolean.valueOf(simpleLight.getOn()));
		return light;
	}

	private List<Light> changeLights(Effect effect, double[] colour, List<Light> lights){
		lights.forEach(light ->{
			light.getState().setOn(true);
			light.getState().setEffect(effect.getValue());
			light.getState().setXy(colour);
		});
		return lights;
	}

	private SimpleLight lightToSimpleLight(Light light) {
		SimpleLight newLight = new SimpleLight();
		newLight.setXy(light.getState().getXy());
		newLight.setId(light.getId());
		newLight.setName(light.getName());
		newLight.setOn(String.valueOf(light.getState().getOn()));
		newLight.setReachable(String.valueOf(light.getState().isReachable()));
		return newLight;
	}


}
