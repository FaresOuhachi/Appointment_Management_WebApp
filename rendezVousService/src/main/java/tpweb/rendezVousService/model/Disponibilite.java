package tpweb.rendezVousService.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilite{
    private Long id;

    private Long professionalId;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;
}
