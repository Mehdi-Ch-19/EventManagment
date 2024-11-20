package net.chiheb.eventmanagment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

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
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    //@JsonManagedReference
    @JsonIgnore
    private Set<Event> events = new HashSet<>();
}
