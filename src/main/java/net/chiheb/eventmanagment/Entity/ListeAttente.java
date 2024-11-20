package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liste_attente",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"participantId","eventId"}
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
    private Long waitinglist;
    @OneToOne
    @JoinColumn(name = "participantId")
    private Participant participant;
    @OneToOne
    @JoinColumn(name="eventId")
    private Event event;
    private int position;
}
