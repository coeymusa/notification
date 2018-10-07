package corey.hue.notifications.model;

public class Trigger {

  private String name;
  private Effect effect;
  private Colour color;
  
  public Effect getEffect() {
    return effect;
  }
  public void setEffect(Effect effect) {
    this.effect = effect;
  }
  public Colour getColor() {
    return color;
  }
  public void setColor(Colour color) {
    this.color = color;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
}
