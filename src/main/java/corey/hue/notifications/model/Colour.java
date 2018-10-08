package corey.hue.notifications.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Colour {
  RED(0),
  BLUE(46920),
  GREEN(25500);

  private int hue;
  
  Colour(int bri){
    this.hue = bri;
  }

  public int getValue() {
    return this.hue;
  }
  
  @JsonValue
  public int toValue() {
      return ordinal();
  }
  
}
