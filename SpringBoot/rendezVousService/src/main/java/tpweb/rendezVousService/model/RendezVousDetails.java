package tpweb.rendezVousService.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendezVousDetails {
    private Long id;
    private Statut statut;

    private Utilisateur client;
    private Utilisateur professional;
    private Disponibilite disponibilite;
}

