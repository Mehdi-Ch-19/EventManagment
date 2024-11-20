package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Service.EventService;
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
    private ResponseEntity<Event> addParticipantToEvent(@PathVariable Long eventid,
                                                        @RequestBody Participant participant) {
        try {
            eventService.addParticipantToEvent(eventid,participant);
            Event event1 = eventService.getEventById(eventid).get();
            return new ResponseEntity<>(event1, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
