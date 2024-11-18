package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organizator extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    private String phoneNumber;
    private String website;
    @OneToMany(mappedBy = "organizator")
    private Set<Event> eventSet;
}
