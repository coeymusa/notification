package corey.hue.notifications.gmail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import corey.hue.notifications.brige.client.HttpClientException;
import corey.hue.notifications.gmail.model.Email;

@Controller
@RequestMapping("/")
public class GmailController {
  
  private EmailService service = new EmailService();
  //Receives requests from a webhook. Currently using automate.io which is connected to gmail account - triggering upon receipt of new email 
  //Sending From, Subject, Body
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<String> newEmailReceived(@RequestBody Email email) throws HttpClientException{
    service.handleEmail(email);
    return ResponseEntity.ok().build();
  }
 
}
