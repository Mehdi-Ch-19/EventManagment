package net.chiheb.eventmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int MaxCapacity;
    @ManyToOne
    @JoinColumn(name = "organizatorId")
    private Organizator organizator;
    @ManyToOne
    @JoinColumn(name = "category")
    @JsonBackReference
    private Category category;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "eventid",referencedColumnName = "eventid"),
            inverseJoinColumns = @JoinColumn(name = "participantId",referencedColumnName = "participantId")
    )
    private Set<Participant> participants = new HashSet<>();

    public void addParticipant(Participant participant) {
        this.getParticipants().add(participant);
    }
}
