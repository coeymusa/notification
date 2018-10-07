package corey.hue.notifications.gmail.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class GmailClient {

  private OkHttpClient httpClient = new OkHttpClient();
  private static final String USER_ID = null;

  public void getEmails(){
    try {
      Request request  = new Request.Builder().
          url(buildGmailGetUrl(USER_ID))
          .build();

      Response response = httpClient.newCall(request).execute();

      String responseBody = response.body().string();
      
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private HttpUrl buildGmailGetUrl(String userId) {
    HttpUrl url = new HttpUrl.Builder()
        .scheme("https")
        .host("www.googleapis.com")
        .addPathSegment("gmail")
        .addPathSegment("v1")
        .addPathSegment("users")
        .addPathSegment("users")
        .addPathSegment(userId)
        .addPathSegment("messages")
        .build();

    return url;

  }
}
