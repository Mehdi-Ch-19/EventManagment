package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.PartcipantRepository;
import org.springframework.stereotype.Service;

@Service
public class PartcipantService {
    private final PartcipantRepository partcipantRepository;

    public PartcipantService(PartcipantRepository partcipantRepository) {
        this.partcipantRepository = partcipantRepository;
    }

    public Participant addParticipant(Participant participant) {
        return partcipantRepository.saveAndFlush(participant);
    }


}
