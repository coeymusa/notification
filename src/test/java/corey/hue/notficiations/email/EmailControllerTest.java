package corey.hue.notficiations.email;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import corey.hue.notifications.email.EmailController;
import corey.hue.notifications.email.EmailService;
import corey.hue.notifications.lights.HttpClientException;
import corey.hue.notifications.model.Email;
import corey.hue.notifications.triggers.TriggerBusinessService;

public class EmailControllerTest {

  @InjectMocks
  private EmailController underTest;

  @Mock
  private EmailService service;
  
  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void shouldCallHandleEmailReceivedPOSTRequest() throws HttpClientException{
    //given
    Email email = mock(Email.class);
    //when
    underTest.newEmailReceived(email);
    //then
    verify(service).handleEmail(email);
  }
  
}
