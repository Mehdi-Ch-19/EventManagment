package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Organizator organizator;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "eventid",referencedColumnName = "eventid"),
            inverseJoinColumns = @JoinColumn(name = "participantId",referencedColumnName = "participantId")
    )
    private Set<Participant> participants;

}
