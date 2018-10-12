package corey.hue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import okhttp3.OkHttpClient;

@Configuration
public class MyConfig {
  
    @Bean()
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

}
  