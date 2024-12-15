package net.chiheb.eventmanagment.Controller;


import net.chiheb.eventmanagment.Dto.ParticipantDto;
import net.chiheb.eventmanagment.Dto.ParticipantEvents;
import net.chiheb.eventmanagment.Dto.mapper.EventPartcipantMapper;
import net.chiheb.eventmanagment.Dto.mapper.ParticipantDtoMapper;
import net.chiheb.eventmanagment.Dto.mapper.ParticipantEventsMapper;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Exeption.EmailAleadyExists;
import net.chiheb.eventmanagment.Service.PartcipantService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/participant")
public class ParticipantController {

    private PartcipantService partcipantService;
    private ParticipantEventsMapper participantEventsMapper;
    private ParticipantDtoMapper participantDtoMapper;

    public ParticipantController(PartcipantService partcipantService, ParticipantEventsMapper participantEventsMapper,
                                 ParticipantDtoMapper participantDtoMapper) {
        this.partcipantService = partcipantService;
        this.participantEventsMapper = participantEventsMapper;
        this.participantDtoMapper = participantDtoMapper;
    }

    @PostMapping
    public ResponseEntity<?> registerParticipant(@RequestBody Participant participant) {
        try {
            Participant participant1 = partcipantService.addParticipant(participant);
            return ResponceHandler.generateResponse("participant added successfuly ", HttpStatus.OK,participant1);
        }catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,null);
        }
    }
    @GetMapping("/{participantid}")
    public  ResponseEntity<?> getParticipantInfo(@PathVariable("participantid") Long id){
        Participant participant = partcipantService.getParticipantById(id);
        return ResponceHandler.generateResponse(
                "partcipant details",
                HttpStatus.OK,
                participantDtoMapper.toParticipantDto(participant)
        );
    }
    @PostMapping("/update/{participantid}")
    public ResponseEntity<?> updatePartcipant(@PathVariable("participantid") Long id ,
                                              @RequestBody ParticipantDto participantDto){
        try {
            Participant participant = partcipantService.updatePartcipant(participantDto, id);
            return ResponceHandler.generateResponse("participant updated ",
                    HttpStatus.OK,
                    participantDtoMapper.toParticipantDto(participant));
        }catch (EmailAleadyExists emailAleadyExists){
            return ResponceHandler.generateResponse(emailAleadyExists.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null);
        }
    }

    @GetMapping("/{participantid}/events")
    public ResponseEntity<?> getParticipantEvents(@PathVariable("participantid") Long participantid) {
        List<EventParticipant> events = partcipantService.getAllEvents(participantid);
        events.forEach(EventParticipant::getEvent);
        List<ParticipantEvents> eventList = new ArrayList<>();
        events.stream().map(eventParticipant -> participantEventsMapper.toParticipantEvents(eventParticipant)).forEach(eventList::add);
        return ResponceHandler.generateResponse("events list ", HttpStatus.OK,
                eventList.stream().sorted(Comparator.comparing(ParticipantEvents::purchaseat)
                        .reversed()).toList());
    }
}
