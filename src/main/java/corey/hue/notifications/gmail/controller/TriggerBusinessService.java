package corey.hue.notifications.gmail.controller;

import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.trigger.TriggerRepository;

public class TriggerBusinessService {

  private TriggerRepository repo = new TriggerRepository();
  
  public void addTrigger(Trigger trigger) {
    repo.addTrigger(trigger); 
  }

}
