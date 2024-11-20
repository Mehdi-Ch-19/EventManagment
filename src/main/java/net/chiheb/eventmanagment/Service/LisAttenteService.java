package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.ListeAttente;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.ListAttentesRepository;
import org.springframework.stereotype.Service;

@Service
public class LisAttenteService {

    private final ListAttentesRepository listAttentesRepository;

    public LisAttenteService(ListAttentesRepository listAttentesRepository) {
        this.listAttentesRepository = listAttentesRepository;
    }

    public void addParticipantToWaitingListofEvent(Participant participant , Event event) {
        ListeAttente listeAttente = new ListeAttente();
        listeAttente.setParticipant(participant);
        listeAttente.setEvent(event);
        listeAttente.setPosition(listAttentesRepository.getMaxPosition(event.getEventid()));
        listAttentesRepository.save(listeAttente);
    }
}
