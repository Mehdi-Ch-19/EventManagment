package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Dto.ParticipantDto;
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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class PartcipantService {
    private final PartcipantRepository partcipantRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventParticipantRepository eventParticipantRepository;

    public PartcipantService(PartcipantRepository partcipantRepository,
                             PasswordEncoder passwordEncoder,
                             EventParticipantRepository eventParticipantRepository) {
        this.partcipantRepository = partcipantRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventParticipantRepository = eventParticipantRepository;
    }
    // add a user as a participant
    public Participant addParticipant(Participant participant) {
        Participant participant1 = partcipantRepository.findParticipantByEmail(participant.getEmail());
        if (participant1 == null) {
            participant.setPassword(passwordEncoder.encode(participant.getPassword()));
            participant.setRole(Role.PARTICIPANT);
            return partcipantRepository.saveAndFlush(participant);
        }else throw new EmailAleadyExists("Email aleardy exists");
    }
    // return all my event
    public List<EventParticipant> getAllEvents(Long participantId) {
        List<EventParticipant> allByParticipant = eventParticipantRepository.
                findAllByParticipant(getParticipantById(participantId));
        System.out.println(allByParticipant.size());
        return allByParticipant;

    }
    public Participant updatePartcipant(ParticipantDto participant , Long id){
        System.out.println(participant.toString());
        Participant participant1 =getParticipantById(id);
        if(!Objects.equals(participant.email(), participant1.getEmail())){
            if(checkIfEmailExists(participant.email())){
                throw new EmailAleadyExists("Email aleardy exists");
            }
        }
        participant1.setAddrese(participant.addrese());
        participant1.setEmail(participant.email());
        participant1.setName(participant.name());
        Participant participant2 = partcipantRepository.saveAndFlush(participant1);
        System.out.println(participant2.getEmail() + participant2.getName() + participant2.getAddrese()) ;
        return participant2;

    }
    public boolean checkIfEmailExists(String email) {
        Participant participantByEmail = partcipantRepository.findParticipantByEmail(email);
        return participantByEmail != null;
    }
    public Participant getParticipantById(Long participantId) {
        return partcipantRepository.findById(participantId).get();
    }

}
