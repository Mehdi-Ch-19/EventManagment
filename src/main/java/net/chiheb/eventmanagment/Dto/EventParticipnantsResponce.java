package net.chiheb.eventmanagment.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record EventParticipnantsResponce(Event event ,
                                         Participant participant ,
                                         @Temporal(TemporalType.TIMESTAMP)
                                         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm") LocalDateTime purchaseat,
                                         boolean isConfirmad) {
    public record Event(Long eventid, String title, LocalDate date){

    }
    public record Participant(Long id, String name, String email){

    }
}
