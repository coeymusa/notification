package corey.hue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication() //scanBasePackages={"corey.hue"}
@EnableAutoConfiguration
public class NotifcationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifcationsApplication.class, args);
	}
}
