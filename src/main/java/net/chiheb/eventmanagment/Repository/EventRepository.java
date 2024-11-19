package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByCategory(Category category);
    List<Event> findAllByOrganizator(Organizator organizator);
    @EntityGraph(attributePaths = {"participants"})
    List<Participant> getParticipantsByEventid(Long eventid);

}
