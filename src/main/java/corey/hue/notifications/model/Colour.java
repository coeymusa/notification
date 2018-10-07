package corey.hue.notifications.model;

public enum Colour {
  RED(65535),
  BLUE(46920),
  GREEN(25500);

  private int hue;
  
  Colour(int bri){
    this.hue = bri;
  }

  public int getValue() {
    return this.hue;
  }
  
}
