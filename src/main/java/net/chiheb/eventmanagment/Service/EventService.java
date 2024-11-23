package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Exeption.AleardyEnrolled;
import net.chiheb.eventmanagment.Exeption.CapacityNotEnoughExeption;
import net.chiheb.eventmanagment.Repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class EventService {

    private EventRepository eventRepository;
    private LisAttenteService lisAttenteService;
    private PartcipantService partcipantService;
    public EventService(EventRepository eventRepository, LisAttenteService lisAttenteService, PartcipantService partcipantService){
        this.eventRepository = eventRepository;
        this.lisAttenteService = lisAttenteService;
        this.partcipantService = partcipantService;
    }
    @Transactional
    public Event createEvent(EventCreationDto eventCreationDto){
        Event e = new Event();
        e.setTitle(eventCreationDto.getTitle());
        e.setDescription(eventCreationDto.getDescription());
        e.setCategory(eventCreationDto.getCategory());
        e.setMaxCapacity(eventCreationDto.getMaxCapacity());
        e.setParticipants(new ArrayList<>());
        e.setOrganizator(eventCreationDto.getOrganizator());
        e.setDate(eventCreationDto.getDate());
        e.getCategory().getEvents().add(e);
        /*e.getCategory().addEvent(e);*/
        e.getOrganizator().getEventSet().add(e);

        return eventRepository.saveAndFlush(e);
    }
    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

     public Event updateEvent(Event event){
        return eventRepository.save(event);
    }
    //@Transactional
    public void addParticipantToEvent(Long eventid, Participant participant){
        Event event = getEventById(eventid).get();
        System.out.println(event.getParticipants().size());
        if(checkifcapacityenough(event)){
            //System.out.println(event.getParticipants());
            if(checkifparticipantexistsonevent(event,participant)){
                System.out.println("Participant already exists");
                throw new AleardyEnrolled("aleady enrollred");
            }else {
                //System.out.println(event);
                event.getParticipants().add(participant);
                participant.getEventList().add(event);
                eventRepository.saveAndFlush(event);
            }

        }else {
            lisAttenteService.addParticipantToWaitingListofEvent(participant,event);
            throw new CapacityNotEnoughExeption("Capacity not enough");
        }
        /*List<Participant> participants = eventRepository.getParticipantsByEventid(event.getEventid());
        participants.add(participant);*/


    }
    public boolean checkifparticipantexistsonevent(Event event , Participant participant){
        List<Participant> participants = event.getParticipants();
        AtomicBoolean exist = new AtomicBoolean(false);
        participants.forEach(participant1 -> {
            if(Objects.equals(participant1.getId(), participant.getId())){
                exist.set(true);
            }
        });
        return exist.get();
    }
    public boolean checkifcapacityenough(Event event){
        return event.getParticipants().size() < event.getMaxCapacity();
    }
    public List<Event> getAllEventsByCategory(Category category) {
        return eventRepository.findAllByCategory(category);
    }
    public List<Event> getAllEventByOrganizator(Organizator organizator){
        return eventRepository.findAllByOrganizator(organizator);
    }
    public void deleteEvent(Event event ){
        try {
            eventRepository.delete(event);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
