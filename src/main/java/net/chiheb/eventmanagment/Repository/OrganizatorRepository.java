package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Organizator;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizatorRepository extends JpaRepository<Organizator, Long> {
    @EntityGraph(attributePaths = {"eventSet"})
    Organizator findByOrganizatorId(Long organizatorId);
}
