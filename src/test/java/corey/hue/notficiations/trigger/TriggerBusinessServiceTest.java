package corey.hue.notficiations.trigger;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.triggers.TriggerBusinessService;
import corey.hue.notifications.triggers.TriggerRepository;

public class TriggerBusinessServiceTest {

  @InjectMocks
  private TriggerBusinessService underTest;
  
  @Mock
  private TriggerRepository repo;
  
  @Mock
  private List<Trigger> mockTriggerList;
  
  @Before
  public void setup() {
      MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void shouldAddTrigger(){
    //given
    //when
    underTest.addTrigger(mock(Trigger.class));
    //then
  }
  
  @Test
  public void shouldFindAllTriggers(){
    //given
    given(repo.findAll()).willReturn(mockTriggerList);
    //when
    underTest.findAllTriggers();
    //then
  }
  
}
