package corey.hue.notifications.triggers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corey.hue.notifications.lights.LightBusinessService;
import corey.hue.notifications.model.Trigger;

@Service
public class TriggerBusinessService {

  @Autowired
  private TriggerRepository repo;
  
	private static final Logger logger = LoggerFactory.getLogger(TriggerBusinessService.class);
	
  public void addTrigger(Trigger trigger) {
	  logger.debug("Adding trigger: " + trigger.getName());
     repo.save(trigger);
  }

  public List<Trigger> findAllTriggers() {
	  logger.debug("Finding all triggers");
    return repo.findAll();
  }

  public Trigger removeTrigger(Trigger trigger) {
	 logger.debug("Removing trigger: " + trigger.getId());
     repo.delete(trigger);
     return trigger;
  }

}
