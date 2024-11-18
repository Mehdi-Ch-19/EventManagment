package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participant extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @ManyToMany(mappedBy = "participants")
    private List<Event> eventList;;
}
