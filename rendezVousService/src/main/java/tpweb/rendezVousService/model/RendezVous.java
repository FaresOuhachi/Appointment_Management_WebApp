package tpweb.rendezVousService.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    private Long clientId;

    private Long professionnelId;
}
