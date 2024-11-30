package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Dto.ParticipantEvents;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventPartcipantPk;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.Participant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, EventPartcipantPk> {
    EventParticipant findEventParticipantByEventAndParticipant(Event event, Participant participant);
   /* @Query(value = "SELECT e.event , e.isConfirmad from EventParticipant e join Participant" +
            " p on e.eventPartcipantPk.participantId = p.id where e.eventPartcipantPk.participantId = :participantId",nativeQuery = false)
    List<EventParticipant> findAllByParticipant(@Param("participantId") Long participantId);*/
   @EntityGraph(attributePaths = {"event","event.organizator","event.category"})
   List<EventParticipant> findAllByParticipant(Participant participant);
}