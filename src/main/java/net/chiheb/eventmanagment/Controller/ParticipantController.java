package net.chiheb.eventmanagment.Controller;


import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Service.PartcipantService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participant")
public class ParticipantController {

    private PartcipantService partcipantService;

    public ParticipantController(PartcipantService partcipantService) {
        this.partcipantService = partcipantService;
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

    @GetMapping("/{participantid}/events")
    public ResponseEntity<?> getParticipantEvents(@PathVariable("participantid") Long participantid) {
        List<Event> events = partcipantService.getAllEvents(participantid);
        return ResponceHandler.generateResponse("events list ", HttpStatus.OK,events);
    }
}
