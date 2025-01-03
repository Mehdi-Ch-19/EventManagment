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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime EventStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime EventEndTime;

    private String location;
    private int MaxCapacity;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "organizatorId")
    private Organizator organizator;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @OneToMany( mappedBy = "event")
    @JsonIgnore
    private List<EventParticipant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        //this.getParticipants().add(participant);
    }
}
