package corey.hue.notficiations.trigger;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.trigger.TriggerBusinessService;
import corey.hue.notifications.trigger.TriggerController;
public class TriggerControllerTest {
  
  @InjectMocks
  private TriggerController underTest;

  @Mock
  private TriggerBusinessService triggerService;
  
  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void shouldCallTriggerRepoWhenReceivedPOSTRequest() throws HttpClientException{
    //given
    Trigger trigger = mock(Trigger.class);
    //when
    underTest.addTrigger(trigger);
    //then
    verify(triggerService).addTrigger(trigger);
  }


}
