package corey.hue.notifications.gmail.model;

public class Email {

  private String fromEmail;
  private String fromName;
  private String body;
  
  public String getFromEmail() {
    return fromEmail;
  }
  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }
  public String getFromName() {
    return fromName;
  }
  public void setFromName(String fromName) {
    this.fromName = fromName;
  }
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }
  
  
}
