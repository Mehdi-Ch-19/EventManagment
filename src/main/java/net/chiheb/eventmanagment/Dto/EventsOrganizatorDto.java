package net.chiheb.eventmanagment.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import net.chiheb.eventmanagment.Entity.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
public  class EventsOrganizatorDto {
    private  Long eventid;
    private  String title;
    private  String description;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private  LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private  LocalDateTime EventStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private  LocalDateTime EventEndTime;
    private  String location;
    private  String imageUrl;
    private  int numOfPartcipant = 0;
    private Category category;


}
