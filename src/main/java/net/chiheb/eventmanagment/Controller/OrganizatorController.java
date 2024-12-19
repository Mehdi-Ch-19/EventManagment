package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.mapper.EventMapper;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Service.OrganizatorService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizator")
public class OrganizatorController {

    private OrganizatorService organizatorService;
    private EventMapper eventMapper;

    public OrganizatorController(OrganizatorService organizatorService, EventMapper eventMapper) {
        this.organizatorService = organizatorService;
        this.eventMapper = eventMapper;
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
        return ResponceHandler.generateResponse("events list ", HttpStatus.OK,events.stream().map(event -> eventMapper.toDto(event)).toList());
    }
}
