package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.*;
import net.chiheb.eventmanagment.Dto.mapper.EventPartcipantMapper;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.EventParticipantRepository;
import net.chiheb.eventmanagment.Service.EmailService;
import net.chiheb.eventmanagment.Service.EventService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;
    private EventPartcipantMapper eventPartcipantMapper;
    private EmailService emailService;
    private EventParticipantRepository eventParticipantRepository;

    public EventController(EventService eventService, EventPartcipantMapper eventPartcipantMapper, EmailService emailService) {
        this.eventService = eventService;
        this.eventPartcipantMapper = eventPartcipantMapper;
        this.emailService = emailService;
    }

    @PostMapping("/{eventid}/enrolle")
    private ResponseEntity<?> addParticipantToEvent(@PathVariable Long eventid,
                                                        @RequestBody ParticipantEventEnrolDto participant) {
        try {
            EventParticipant eventParticipant = eventService.addParticipantToEventFront(eventid, participant);
            EventParticipnantsResponce eventParticipnantsResponce = eventPartcipantMapper.toEventParticipnantsResponce(eventParticipant);

            return  ResponceHandler.generateResponse("Participant added successfuly " , HttpStatus.OK , eventParticipnantsResponce );
        }catch (Exception e){
            e.printStackTrace();
            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR , null );
        }

    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllEvents(){
        List<EventDto> events = eventService.getallevents();
        return ResponceHandler.generateResponse("all the events", HttpStatus.OK , events);
    }


    @GetMapping("/category")
    public ResponseEntity<?> getEventsByCategory(@RequestParam String type){
        List<Event> events = eventService.getAllEventsByCategory(type);
        return ResponceHandler.generateResponse("all the events", HttpStatus.OK , events);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventCreationFrontDto event){
        try {
            Event event1 = eventService.createEventFront(event);
            return ResponceHandler.generateResponse("created succufuly", HttpStatus.OK , event1);
        }catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }

    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        try {
            emailService.confirmToken(token);
            EventParticipant eventParticipant = emailService.getEventParticipantFromToken(token);
            EventParticipnantsResponce eventParticipnantsResponce = eventPartcipantMapper.toEventParticipnantsResponce(eventParticipant);
            /*return ResponceHandler.generateResponse("confirmad participant ",HttpStatus.OK,
                    eventParticipnantsResponce);*/
            return "Your participation has been confirmed";
        }catch (Exception e){
            /*return ResponceHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,
                    null);*/
            return e.getMessage();
        }
     }

}
