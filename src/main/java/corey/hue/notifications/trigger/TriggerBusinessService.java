package corey.hue.notifications.trigger;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corey.hue.notifications.model.Trigger;

@Service
public class TriggerBusinessService {

  @Autowired
  private TriggerRepository repo;
  
  public void addTrigger(Trigger trigger) {
     repo.save(trigger);
  }

  public List<Trigger> findAllTriggers() {
    return repo.findAll();
  }

}
