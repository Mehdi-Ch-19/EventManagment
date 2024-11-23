package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Entity.Role;
import net.chiheb.eventmanagment.Exeption.EmailAleadyExists;
import net.chiheb.eventmanagment.Repository.PartcipantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartcipantService {
    private final PartcipantRepository partcipantRepository;
    private final PasswordEncoder passwordEncoder;

    public PartcipantService(PartcipantRepository partcipantRepository, PasswordEncoder passwordEncoder) {
        this.partcipantRepository = partcipantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Participant addParticipant(Participant participant) {
        Participant participant1 = partcipantRepository.findParticipantByEmail(participant.getEmail());
        if (participant1 == null) {
            participant.setPassword(passwordEncoder.encode(participant.getPassword()));
            participant.setRole(Role.ROLE_PARTICIPANT);
            return partcipantRepository.saveAndFlush(participant);
        }else throw new EmailAleadyExists("Email aleardy exists");
    }
    public List<Event> getAllEvents(Long participantId) {
        return partcipantRepository.findById(participantId).get().getEventList();
    }
    public Participant getParticipantById(Long participantId) {
        return partcipantRepository.findParticipantById(participantId).get();
    }

}
