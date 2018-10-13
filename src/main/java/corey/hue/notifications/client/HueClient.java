package corey.hue.notifications.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import corey.hue.notifications.model.Light;
import corey.hue.notifications.model.State;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class HueClient {

  private static final String USERNAME = "-pANyxiz-cp5GhL2wSXOELEBfClWStqaKs5mXGQr";
  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  @Autowired
  private OkHttpClient httpClient;

  @Value("${corey.hue.client.ip:192.168.0.8}")
  private String hueIp; 

  @SuppressWarnings("unchecked")
  public List<Light> getLights(String id) throws HttpClientException  {
    try {
      Request request = new Request.Builder()
          .url(buildUrlForGetLights())
          .build();

      Response response = httpClient.newCall(request).execute();
      String responseBody= response.body().string();

      return  populateLightsFromResponseBody(id,responseBody);
    } catch (IOException | JSONException err) {
      throw new HttpClientException("Error whilst requesting light data", err);
    }
  }


  public List<String> postLights(List<Light> lights) throws HttpClientException{
    List<String> responses = new ArrayList();
    lights.forEach(light -> {
      try{
        String basicJson = new Gson().toJson(light.getState());
        String state = removeUnavailableParams(basicJson);
        RequestBody body = RequestBody.create(JSON, state);
        Request request = new Request.Builder()
            .url(buildUrlForPostLights(String.valueOf(light.getId())))
            .put(body)
            .build();
        Response response = httpClient.newCall(request).execute();
        responses.add(response.body().string());
        System.out.println(responses.get(0).toString());
      } catch (IOException | JSONException err) {
        System.out.println(err);
      }
    });
    return responses;
  }
  
  private List<Light> populateLightsFromResponseBody(String id, String responseBody) throws NumberFormatException, JSONException {
    List<Light> lights = new ArrayList<Light>();
    //if no specific requested get all
    if(id == null){
      final JSONObject obj = new JSONObject(responseBody);
      for(int i = 1; i < obj.length() + 1;i++){
        Light light = setVariablesForLight(responseBody, i);
        lights.add(light);
      }
      //get light with id
    }else{
      Light light = setVariablesForLight(responseBody,Integer.valueOf(id));
      lights.add(light);
    }
    return lights;
  }

  private String removeUnavailableParams(String jsonString) throws JSONException {
    final JSONObject obj = new JSONObject(jsonString);
    if(obj.get("colorMode") != null){
      obj.remove("colorMode");
    }
    if(obj.get("reachable") != null){
      obj.remove("reachable");
    }
    return obj.toString();
  }

  private Light setVariablesForLight( String jsonString, int i) throws JSONException {
    Light light = new Light();
    final JSONObject obj = new JSONObject(jsonString);
    final String stateData = obj.getJSONObject(String.valueOf(i)).getString("state");
    final JSONObject data = obj.getJSONObject(String.valueOf(i));
    light.setId(i);
    light.setState(State.fromJson(stateData));
    light.setName(data.getString("name"));
    light.setType(data.getString("type"));
    light.setUniqueId(data.getString("uniqueid")); 
    return light;
  }

  private HttpUrl buildUrlForGetLights() {
    HttpUrl url = new HttpUrl.Builder()
        .scheme("http")
        .host("192.168.0.8")
        .addPathSegment("api")
        .addPathSegment(USERNAME)
        .addPathSegment("lights")
        .build();
    return url;
  }

  private HttpUrl buildUrlForPostLights(String id) {
    HttpUrl url = new HttpUrl.Builder()
        .scheme("http")
        .host("192.168.0.8")
        .addPathSegment("api")
        .addPathSegment(USERNAME)
        .addPathSegment("lights")
        .addPathSegment(id)
        .addPathSegment("state")
        .build();
    return url;
  }

}