package net.chiheb.eventmanagment.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Organizator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationFrontDto {
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private String imageUrl;
    private int MaxCapacity;
    private Long organizatorid;
    private Long categoryid;
}
