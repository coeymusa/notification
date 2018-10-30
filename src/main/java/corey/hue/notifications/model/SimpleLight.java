package corey.hue.notifications.model;

public class SimpleLight {
  private int id;
  private String on;
  private double[] xy;
  private String name;
  private String reachable;
  
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getOn() {
    return on;
  }
  public void setOn(String on) {
    this.on = on;
  }
  public double[] getXy() {
    return xy;
  }
  public void setXy(double[] xy) {
    this.xy = xy;
  }
  public String getReachable() {
    return reachable;
  }
  public void setReachable(String reachable) {
    this.reachable = reachable;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  
}
