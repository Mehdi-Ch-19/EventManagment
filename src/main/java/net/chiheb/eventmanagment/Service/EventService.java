package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Dto.EventCreationDto;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class EventService {

    private EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventCreationDto eventCreationDto){
        Event e = Event.builder().title(eventCreationDto.getTitle())
                .date(eventCreationDto.getDate())
                .description(eventCreationDto.getDescription())
                .category(eventCreationDto.getCategory())
                .MaxCapacity(eventCreationDto.getMaxCapacity())
                .organizator(eventCreationDto.getOrganizator())
                .participants(new HashSet<>())
                .build();
        e.getCategory().getEvents().add(e);
        e.getOrganizator().getEventSet().add(e);
        return eventRepository.saveAndFlush(e);
    }
    public Event createNewEvent(Event event){
        event.setParticipants(new HashSet<>());
        return eventRepository.saveAndFlush(event);
    }
     public Event updateEvent(Event event){
        return eventRepository.save(event);
    }
    @Transactional
    public Event addParticipantToEvent(Event event, Participant participant){
        if(checkifcapacityenough(event)){
            event.getParticipants().add(participant);
        }
        /*List<Participant> participants = eventRepository.getParticipantsByEventid(event.getEventid());
        participants.add(participant);*/

        return eventRepository.save(event);
    }
    public boolean checkifcapacityenough(Event event){
        if(event.getParticipants().size()<event.getMaxCapacity()){
            return true;
        }else return false;
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
