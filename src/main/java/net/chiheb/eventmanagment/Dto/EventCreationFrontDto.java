package net.chiheb.eventmanagment.Dto;

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
    private LocalDate date;
    private String location;
    private String imageUrl;
    private int MaxCapacity;
    private Long organizatorid;
    private Long categoryid;
}
