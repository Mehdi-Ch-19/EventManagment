package net.chiheb.eventmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Participant extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private Set<Event> eventList = new HashSet<>();

}
