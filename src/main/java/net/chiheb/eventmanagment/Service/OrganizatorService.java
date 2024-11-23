package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Role;
import net.chiheb.eventmanagment.Exeption.EmailAleadyExists;
import net.chiheb.eventmanagment.Repository.OrganizatorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private final EventService eventService;
    private final PasswordEncoder passwordEncoder;
    public OrganizatorService(OrganizatorRepository organizatorRepository, EventService eventService, PasswordEncoder passwordEncoder) {
        this.organizatorRepository = organizatorRepository;
        this.eventService = eventService;
        this.passwordEncoder = passwordEncoder;
    }
    public Organizator createOrganizator(Organizator organizator) {
        Organizator organizator1 = organizatorRepository.findOrganizatorByEmail(organizator.getEmail());
        if(organizator1 == null){
            organizator.setPassword(passwordEncoder.encode(organizator.getPassword()));
            organizator.setRole(Role.ROLE_ORGANIZATOR);
            return organizatorRepository.save(organizator);
        }else {
            throw new EmailAleadyExists("Email aleady exists" );
        }
    }
    @Transactional
    public List<Event> getOrganizatorEvents(Long organizatorid) {
        return organizatorRepository.findById(organizatorid).get().getEventSet();
    }
    public Organizator getOrganizatorById(Long id){
        return organizatorRepository.findById(id).get();
    }

}
