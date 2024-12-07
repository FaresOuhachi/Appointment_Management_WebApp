package tp.ingweb.gatewayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("utilisateur-service-route", r -> r.path("/utilisateurs/**")
						.uri("http://localhost:8081"))
				.route("rendezvous-service-route", r -> r.path("/rendezvous/**")
						.uri("http://localhost:8082"))
				.route("disponibilite-service-route", r -> r.path("/disponibilites/**")
						.uri("http://localhost:8083"))
				.build();
	}


}
