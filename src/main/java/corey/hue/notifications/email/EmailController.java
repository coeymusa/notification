package corey.hue.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import corey.hue.notifications.lights.HttpClientException;
import corey.hue.notifications.model.Email;

@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService service;

	@RequestMapping(value= "/new",method = RequestMethod.POST)
	public void newEmailReceived(@RequestBody Email email) throws HttpClientException{
		service.handleEmail(email);
	}


}
