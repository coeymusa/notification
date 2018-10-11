package corey.hue.notifications.trigger;

import org.springframework.data.mongodb.repository.MongoRepository;
import corey.hue.notifications.model.Trigger;

public interface TriggerRepository extends MongoRepository<Trigger, String>{

  public Trigger save(Trigger trigger);

}


