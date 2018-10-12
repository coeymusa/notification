package corey.hue.notficiations.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.client.HueClient;
import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Light;
import corey.hue.notifications.model.State;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.service.LightService;
import corey.hue.notifications.trigger.TriggerRepository;

public class LightBusinessServiceTest {

  @InjectMocks
  private LightService underTest;
  
  @Mock
  private HueClient hueClient;
  
  @Mock
  private TriggerRepository repo;
  
  @Mock
  private List<Trigger> mockTriggerList;
 
  @Captor
  private ArgumentCaptor<List<Light>> argCaptor;
    
  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldHandleEmail() throws HttpClientException, InterruptedException{
    //given
    boolean on = false;
    List<Light> lights = new ArrayList<Light>();
    Light light = generateLight(on);
    lights.add(light);
    given(hueClient.getLights(null)).willReturn(lights);
    double[] colour ={0.25,0.50};
    Effect effect = Effect.NONE;
    List<Light> expectedNewLights = generateChangedLights(effect,colour);
     //when
    underTest.handleRequest(effect, colour);
    //then
    verify(hueClient,times(2)).postLights(argCaptor.capture());
    List<Light> captorLights = argCaptor.getValue();
    Assert.assertEquals(captorLights.get(0).getState().getXy(), colour);
    Assert.assertEquals(captorLights.get(0).getState().getEffect(), effect.getValue());
    Assert.assertEquals(captorLights.get(0).getState().getOn(), !on);
  }
  
  private List<Light> generateChangedLights(Effect effect, double[] colour) {
    List<Light> lights = new ArrayList<Light>();
    Light light = generateLight(true);
    State state = new State();
    
    state.setEffect(effect.getValue());
    state.setXy(colour);
    light.setState(state);
    
    return lights;
  }
  

  @Test
  public void shouldTurnLightsOn() throws HttpClientException, InterruptedException{
    //given
    boolean on = false;
    List<Light> lights = new ArrayList<Light>();
    Light light = generateLight(on);
    lights.add(light);
    //when
    List<Light> result = underTest.turnLightsOn(lights);
    //then
    Assert.assertNotEquals(result.get(0).getState().getOn(), on);
  }
  
  @Test
  public void shouldTurnLightsOff() throws HttpClientException, InterruptedException{
    //given
    boolean on = true;
    List<Light> lights = new ArrayList<Light>();
    Light light = generateLight(on);
    lights.add(light);
    //when
    List<Light> result = underTest.turnLightsOff(lights);
    //then
    Assert.assertNotEquals(result.get(0).getState().getOn(), on);
  }
 
  private Light generateLight(boolean on) {
    Light light = new Light();
    light.setId(2);
    light.setName("Light");
    State state = new State();
    state.setOn(on);
    light.setState(state);
    return light;
  } 
}
