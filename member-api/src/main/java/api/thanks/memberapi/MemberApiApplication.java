package api.thanks.memberapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class MemberApiApplication {

	@Bean
	Logger logger(){
		return Logger.getLogger("member");
	}

	public static void main(String[] args) {
		SpringApplication.run(MemberApiApplication.class, args);
	}
}
