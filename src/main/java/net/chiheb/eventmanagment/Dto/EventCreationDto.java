package net.chiheb.eventmanagment.Dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Organizator;

import java.time.LocalDate;

@Data
@Builder
public class EventCreationDto {
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int MaxCapacity;
    private Organizator organizator;
    private Category category;
}
