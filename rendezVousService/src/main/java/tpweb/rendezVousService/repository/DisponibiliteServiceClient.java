package tpweb.rendezVousService.repository;


import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import tpweb.rendezVousService.model.Disponibilite;

@FeignClient(name = "disponibilite-service",url = "localhost:8083/disponibilites",fallback = DisponibiliteServiceFallback.class) // Replace URL with Eureka name if available
public interface DisponibiliteServiceClient {
    @GetMapping("/{id}")
    Disponibilite getDisponibiliteById(@PathVariable("id") Long id);
}

