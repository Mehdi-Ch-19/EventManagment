package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Service.EventService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{eventid}/enrolle")
    private ResponseEntity<?> addParticipantToEvent(@PathVariable Long eventid,
                                                        @RequestBody Participant participant) {
        try {
            eventService.addParticipantToEvent(eventid,participant);
            Event event1 = eventService.getEventById(eventid).get();
            return  ResponceHandler.generateResponse("Participant added successfuly to " + event1.getTitle(), HttpStatus.OK , event1 );
        }catch (Exception e){
            e.printStackTrace();
            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR , null );
        }

    }
}
