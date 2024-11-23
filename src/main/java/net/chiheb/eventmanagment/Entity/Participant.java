package net.chiheb.eventmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Participant extends User{


    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Event> eventList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));

    }
}
