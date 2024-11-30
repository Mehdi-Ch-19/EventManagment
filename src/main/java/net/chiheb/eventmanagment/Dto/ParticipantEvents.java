package net.chiheb.eventmanagment.Dto;

import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParticipantEvents(Event event , LocalDateTime purchaseat, boolean isConfirmad) {
    public record event(String title,
                        String description,
                        LocalDate date,
                        String location ,Category category ){

        public record Category(String categoryName){

        }
    }
}

