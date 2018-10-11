package corey.hue.notifications.model;

import javax.swing.text.Element;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Trigger {
  
  @Id
  @Field("name")
  private String name;
  @Field("effect")
  private Effect effect;
  @Field("colour")
  private double[] colour;

  public Effect getEffect() {
    return effect;
  }
  public void setEffect(Effect effect) {
    this.effect = effect;
  }
  public double[] getColour() {
    return colour;
  }
  public void setColour(double[] colour) {
    this.colour = colour;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String toStringXy() {
    double[] xy = this.getColour();
    double x = xy[0];
    double y = xy[1];
    return String.valueOf(x) + "," + String.valueOf(y);
  }

  public static double[] fromStringXy(String xyString) {
    String[] split = xyString.split(",");
    double x =     Double.valueOf(split[0]);
    double y=     Double.valueOf(split[1]);
    double[] xy= new double[]{x,y};
    return xy;
  }

}
