package corey.hue.notifications.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class State {

  private boolean on;
  private int bri;
  private int hue;
  private int sat;
  private String effect;
  private double[] xy;
  private int ct;
  private String alert;
  private String colorMode;
  private String mode;
  private boolean reachable;

  public boolean isOn() {
    return on;
  }
  public void setOn(boolean on) {
    this.on = on;
  }
  public int getBri() {
    return bri;
  }
  public void setBri(int bri) {
    this.bri = bri;
  }
  public int getHue() {
    return hue;
  }
  public void setHue(int hue) {
    this.hue = hue;
  }
  public int getSat() {
    return sat;
  }
  public void setSat(int sat) {
    this.sat = sat;
  }
  public String getEffect() {
    return effect;
  }
  public void setEffect(String effect) {
    this.effect = effect;
  }
  public int getCt() {
    return ct;
  }
  public void setCt(int ct) {
    this.ct = ct;
  }
  public String getAlert() {
    return alert;
  }
  public void setAlert(String alert) {
    this.alert = alert;
  }
  public String getColorMode() {
    return colorMode;
  }
  public void setColorMode(String colorMode) {
    this.colorMode = colorMode;
  }
  public String getMode() {
    return mode;
  }
  public void setMode(String mode) {
    this.mode = mode;
  }
  public boolean isReachable() {
    return reachable;
  }
  public void setReachable(boolean reachable) {
    this.reachable = reachable;
  }
  public double[] getXy() {
    return xy;
  }
  public void setXy(double[] xy) {
    this.xy = xy;
  }
  public static State fromJson(String stateData) throws JSONException {
    State state = new State();
    final JSONObject obj = new JSONObject(stateData);
    state.setAlert(obj.getString("alert"));
    state.setBri(obj.getInt("bri"));
    state.setColorMode(obj.getString("colormode"));
    state.setCt(obj.getInt("ct"));
    state.setHue(obj.getInt("hue"));
    state.setMode(obj.getString("mode"));
    state.setSat(obj.getInt("sat"));
    state.setEffect(obj.getString("effect"));
    JSONArray outerArray = obj.getJSONArray("xy");
   
    double[] xy = parseXy(outerArray);
    state.setXy(xy);
    return state;
  }
  
  private static double[] parseXy(JSONArray array) throws JSONException {
    double[] result = new double[2];
    for(int i =0; i < array.length(); i++){
      result[i] = array.getDouble(i);
    }
    
    return result;
  }

}
