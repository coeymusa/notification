package corey.hue.notficiations.email;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import corey.hue.notifications.client.HttpClientException;
import corey.hue.notifications.email.EmailService;
import corey.hue.notifications.gmail.model.Email;
import corey.hue.notifications.model.Effect;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.service.LightService;
import corey.hue.notifications.trigger.TriggerBusinessService;

public class EmailServiceTest {

  @InjectMocks
  private EmailService underTest;

  @Mock
  private TriggerBusinessService triggerService;

  @Mock
  LightService lightService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldFindTriggerInEmailSubject() throws HttpClientException, InterruptedException{
    //given
    List<Trigger> triggers = poulateTriggers();
    Email email = new Email();
    email.setSubject("test tset");
    given(triggerService.findAllTriggers()).willReturn(triggers);
    //when
    underTest.handleEmail(email);
    //then
    verify(lightService).handleRequest(triggers.get(0).getEffect(), triggers.get(0).getColour());
  }
  
  @Test
  public void shouldNotFindTriggerInEmailSubject() throws HttpClientException, InterruptedException{
    //given
    List<Trigger> triggers = poulateTriggers();
    Email email = new Email();
    email.setSubject("failPls");
    given(triggerService.findAllTriggers()).willReturn(triggers);
    //when
    underTest.handleEmail(email);
    //then
    verify(lightService, never()).handleRequest(triggers.get(0).getEffect(), triggers.get(0).getColour());
  }
  

  private List<Trigger> poulateTriggers() {
    List<Trigger> triggerList = new ArrayList<Trigger>();
    Trigger trigger= new Trigger();
    trigger.setEffect(Effect.NONE);
    trigger.setColour(new double[]{0.25,0.50});
    trigger.setName("test");
    triggerList.add(trigger);
    return triggerList;
  }

}
