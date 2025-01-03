package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.ListeAttente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListAttentesRepository extends JpaRepository<ListeAttente,Long> {
    @Query(value = "SELECT COALESCE(MAX(position), 0) + 1 FROM liste_attentes" +
            " WHERE liste_attentes.event_id = ?1",nativeQuery = true)
    int getMaxPosition(Long eventid);
    @Query(
            value = "SELECT participant_id FROM liste_attentes where event_id=?1 ORDER BY position LIMIT 1"
    ,nativeQuery = true)
    Long getFirstPartcipantInWaitingList(Long eventid);

}
