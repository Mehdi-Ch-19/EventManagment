package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Repository.OrganizatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private final EventService eventService;
    public OrganizatorService(OrganizatorRepository organizatorRepository, EventService eventService) {
        this.organizatorRepository = organizatorRepository;
        this.eventService = eventService;
    }
    @Transactional
    public Set<Event> getOrganizatorEvents(Organizator organizator) {
        return organizatorRepository.findByOrganizatorId(organizator.getOrganizatorId()).getEventSet();
    }
    public Organizator createOrganizator(Organizator organizator) {
        return organizatorRepository.save(organizator);
    }

}
