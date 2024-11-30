package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Organizator;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizatorRepository extends JpaRepository<Organizator, Long> {
    @EntityGraph(attributePaths = {"eventSet"})
    Optional<Organizator> findById(Long organizatorId);
    Organizator findOrganizatorByEmail(String email);
}

