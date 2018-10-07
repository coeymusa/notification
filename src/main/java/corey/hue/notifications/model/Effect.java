package corey.hue.notifications.model;

public enum Effect {

  //https://www.developers.meethue.com/documentation/lights-api#16_set_light_state - only ones currently supported
  NONE("none"),
  COLORLOOP("colorloop");
  
  String effect;
  
  Effect(String effect){
    this.effect = effect;
  }

  public String getEffect() {
    return effect;
  }
 
  
}
