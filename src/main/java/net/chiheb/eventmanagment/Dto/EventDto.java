package net.chiheb.eventmanagment.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventDto(
         String title,
         String description,
        @Temporal(TemporalType.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
         LocalDate date,
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")
         @Temporal(TemporalType.TIMESTAMP)
         LocalDateTime EventStartTime,
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm")
        @Temporal(TemporalType.TIMESTAMP)
         LocalDateTime EventEndTime,
         String location,
         String imageUrl
) {
}