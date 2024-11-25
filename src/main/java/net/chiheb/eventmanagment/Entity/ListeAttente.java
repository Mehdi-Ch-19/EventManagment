package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "liste_attentes",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"participant_id","event_id"}
))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ListeAttente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listeattenteId;
    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;
    @OneToOne
    @JoinColumn(name="event_id")
    private Event event;
    private int position;
}
