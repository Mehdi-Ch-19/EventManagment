package net.chiheb.eventmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private int MaxCapacity;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "organizatorId")
    private Organizator organizator;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    //@JsonBackReference
    private Category category;
    @OneToMany( mappedBy = "event")
    @JsonIgnore
    private List<EventParticipant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        //this.getParticipants().add(participant);
    }
}
