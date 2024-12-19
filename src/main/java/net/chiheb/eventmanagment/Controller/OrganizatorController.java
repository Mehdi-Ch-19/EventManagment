package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.EventsOrganizatorDto;
import net.chiheb.eventmanagment.Dto.mapper.EventMapper;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Service.EventService;
import net.chiheb.eventmanagment.Service.OrganizatorService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/organizator")
public class OrganizatorController {

    private OrganizatorService organizatorService;
    private EventMapper eventMapper;
    private EventService eventService;

    public OrganizatorController(OrganizatorService organizatorService, EventMapper eventMapper, EventService eventService) {
        this.organizatorService = organizatorService;
        this.eventMapper = eventMapper;
        this.eventService = eventService;
    }
    @PostMapping
    public ResponseEntity<?> registerOrganizator(@RequestBody Organizator organizator) {
        try {
            Organizator organizator1 = organizatorService.createOrganizator(organizator);
            return ResponceHandler.generateResponse("organizator added successfuly ", HttpStatus.OK,organizator1);
        }catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,null);
        }
    }

    @GetMapping("/{organizatorId}/events")
    public ResponseEntity<?> getOrganizatorEvents(@PathVariable Long organizatorId) {
        List<Event> events = organizatorService.getOrganizatorEvents(organizatorId);
        List<EventsOrganizatorDto> eventsOrganizatorDtos = new ArrayList<>();
        events.forEach(event -> {
            EventsOrganizatorDto eventsOrganizatorDto = new EventsOrganizatorDto();
            eventsOrganizatorDto.setEventStartTime( event.getEventStartTime() );
            eventsOrganizatorDto.setEventEndTime( event.getEventEndTime() );
            eventsOrganizatorDto.setEventid( event.getEventid() );
            eventsOrganizatorDto.setTitle( event.getTitle() );
            eventsOrganizatorDto.setDescription( event.getDescription() );
            eventsOrganizatorDto.setDate( event.getDate() );
            eventsOrganizatorDto.setLocation( event.getLocation() );
            eventsOrganizatorDto.setImageUrl( event.getImageUrl() );
            eventsOrganizatorDto.setNumOfPartcipant(eventService.numOfParticipantInEvent(event.getEventid()));
            eventsOrganizatorDtos.add(eventsOrganizatorDto);
        });
        return ResponceHandler.generateResponse("events list ", HttpStatus.OK, eventsOrganizatorDtos);
    }
}
