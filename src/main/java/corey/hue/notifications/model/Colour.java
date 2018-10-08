package corey.hue.notifications.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Colour {
  RED(new double[]{0.70, 0.25}),
  BLUE(new double[]{0.15,0.22}),
  GREEN(new double[]{0.10,0.80});

  private double[] hue;
  
  Colour(double[] hue){
    this.hue = hue;
  }

  public double[] getValue() {
    return this.hue;
  }
  
  @JsonValue
  public int toValue() {
      
      return ordinal();
  }
  
}
