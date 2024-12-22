package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.*;
import net.chiheb.eventmanagment.Dto.mapper.EventMapper;
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
    private EventMapper eventMapper;

    public EventController(EventService eventService, EventPartcipantMapper eventPartcipantMapper, EmailService emailService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventPartcipantMapper = eventPartcipantMapper;
        this.emailService = emailService;
        this.eventMapper = eventMapper;
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
    @GetMapping("/{eventid}")
    public ResponseEntity<?> getEventById(@PathVariable Long eventid){
        Event event = eventService.getEventById(eventid).get();
        System.out.println("id = " + event.getCategory().getCategoryId() + "" +
                "name"+event.getCategory().getCategoryName());
        return ResponceHandler.generateResponse("Single Event",
                HttpStatus.OK ,eventMapper.toDto(event));

    }


    @GetMapping("/category")
    public ResponseEntity<?> getEventsByCategory(@RequestParam String type){
        List<EventDto> events = eventService.getAllEventsByCategory(type);
        return ResponceHandler.generateResponse("all the events by Category", HttpStatus.OK , events);
    }
    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingEvents(){
        List<EventDto> events = eventService.getUpcomingEvents();
        return ResponceHandler.generateResponse("all upcoming events", HttpStatus.OK , events);
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
    @GetMapping("/check")
        public ResponseEntity<Boolean> checkIfPartcipantExistOnTheEvent(@RequestParam Long eventid,@RequestParam Long partcipantId){
        return new ResponseEntity<>(eventService.checkifparticipantexistsonevent(eventid,partcipantId),HttpStatus.OK);
        
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody EventDto eventDto){
        try {
            eventService.updateEvent(eventDto);
            return ResponceHandler.generateResponse("updated succufuly", HttpStatus.OK , eventDto);
        }catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);

        }
    }

    @DeleteMapping("/{eventid}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventid){
        try {
            eventService.deleteEvent(eventid);
            return ResponceHandler.generateResponse("event deleted successfuly",HttpStatus.OK,null);
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
