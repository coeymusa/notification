package corey.hue.notifications.model;

public class Trigger {

  private String name;
  private Effect effect;
  private Colour colour;
  
  public Effect getEffect() {
    return effect;
  }
  public void setEffect(Effect effect) {
    this.effect = effect;
  }
  public Colour getColour() {
    return colour;
  }
  public void setColour(Colour colour) {
    this.colour = colour;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
}
