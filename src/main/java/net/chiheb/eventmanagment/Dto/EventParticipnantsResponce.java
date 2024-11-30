package net.chiheb.eventmanagment.Dto;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;

import java.time.LocalDateTime;
import java.util.Date;

public record EventParticipnantsResponce(Event event , Participant participant ,LocalDateTime purchaseat,  boolean isConfirmad) {
    public record Event(Long eventid, String title, Date date){

    }
    public record Participant(Long id, String name, String email){

    }
}
