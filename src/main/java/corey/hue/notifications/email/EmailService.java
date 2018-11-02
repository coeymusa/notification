package corey.hue.notifications.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import corey.hue.notifications.lights.HttpClientException;
import corey.hue.notifications.lights.LightBusinessService;
import corey.hue.notifications.model.Email;
import corey.hue.notifications.model.Trigger;
import corey.hue.notifications.triggers.TriggerBusinessService;

@Service
public class EmailService {

	@Value("${corey.hue.email.triggers:Shane Dawson}")
	public String trigger;

	@Autowired
	private TriggerBusinessService triggerService;

	@Autowired
	LightBusinessService lightService = new LightBusinessService();

	public void handleEmail(Email email) throws HttpClientException {
		List<Trigger> triggers = triggerService.findAllTriggers();

		if(email != null){
			triggers.forEach(trigger -> {
				if(email.getSubject().toLowerCase().contains((trigger.getName().toLowerCase()))){
					try {
						lightService.handleRequest(trigger.getEffect(),trigger.getColour());
					} catch (HttpClientException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}


}
