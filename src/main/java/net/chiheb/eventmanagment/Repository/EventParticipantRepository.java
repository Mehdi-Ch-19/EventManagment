package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventPartcipantPk;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, EventPartcipantPk> {
    EventParticipant findEventParticipantByEventAndParticipant(Event event, Participant participant);
}
