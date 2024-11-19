package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Repository.OrganizatorRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private final EventService eventService;
    public OrganizatorService(OrganizatorRepository organizatorRepository, EventService eventService) {
        this.organizatorRepository = organizatorRepository;
        this.eventService = eventService;
    }

    public Organizator createOrganizator(Organizator organizator) {
        return organizatorRepository.save(organizator);
    }

}
