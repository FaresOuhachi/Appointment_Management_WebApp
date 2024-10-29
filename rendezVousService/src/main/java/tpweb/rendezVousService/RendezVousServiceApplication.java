package tpweb.rendezVousService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RendezVousServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RendezVousServiceApplication.class, args);
	}

}
