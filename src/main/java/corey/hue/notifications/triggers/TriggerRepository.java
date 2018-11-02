package corey.hue.notifications.triggers;

import org.springframework.data.mongodb.repository.MongoRepository;
import corey.hue.notifications.model.Trigger;

public interface TriggerRepository extends MongoRepository<Trigger, String>{

  public Trigger save(Trigger trigger);

}


