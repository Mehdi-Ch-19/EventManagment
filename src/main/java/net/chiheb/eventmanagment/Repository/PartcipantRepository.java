package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Participant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartcipantRepository extends JpaRepository<Participant,Long> {
    @EntityGraph(attributePaths = {"eventList"})
    Optional<Participant> findParticipantById(Long id);
    Participant findParticipantByEmail(String email);
}
