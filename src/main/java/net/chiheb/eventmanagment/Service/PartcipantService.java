package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Entity.Role;
import net.chiheb.eventmanagment.Exeption.EmailAleadyExists;
import net.chiheb.eventmanagment.Repository.EventParticipantRepository;
import net.chiheb.eventmanagment.Repository.PartcipantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartcipantService {
    private final PartcipantRepository partcipantRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventParticipantRepository eventParticipantRepository;

    public PartcipantService(PartcipantRepository partcipantRepository, PasswordEncoder passwordEncoder, EventParticipantRepository eventParticipantRepository) {
        this.partcipantRepository = partcipantRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventParticipantRepository = eventParticipantRepository;
    }

    public Participant addParticipant(Participant participant) {
        Participant participant1 = partcipantRepository.findParticipantByEmail(participant.getEmail());
        if (participant1 == null) {
            participant.setPassword(passwordEncoder.encode(participant.getPassword()));
            participant.setRole(Role.PARTICIPANT);
            return partcipantRepository.saveAndFlush(participant);
        }else throw new EmailAleadyExists("Email aleardy exists");
    }
    public List<EventParticipant> getAllEvents(Long participantId) {
        List<EventParticipant> allByParticipant = eventParticipantRepository.findAllByParticipant(getParticipantById(participantId));

        System.out.println(allByParticipant.size());
        return allByParticipant;
        /*return eventParticipantRepository.
                findAllByParticipant(getParticipantById(participantId));*/
    }

    public Participant getParticipantById(Long participantId) {
        return partcipantRepository.findById(participantId).get();
    }

}
