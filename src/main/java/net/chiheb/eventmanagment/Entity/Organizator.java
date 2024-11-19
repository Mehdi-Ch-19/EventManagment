package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Organizator extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizatorId;
    private String phoneNumber;
    private String website;
    @OneToMany(mappedBy = "organizator",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private Set<Event> eventSet = new HashSet<>();
}
