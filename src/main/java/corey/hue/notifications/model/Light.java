package corey.hue.notifications.model;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Light {

  private int id;
  private State state;
  private String type;
  private String name;
  private String uniqueId;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public State getState() {
    return state;
  }
  public void setState(State state) {
    this.state = state;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getUniqueId() {
    return uniqueId;
  }
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }



}
