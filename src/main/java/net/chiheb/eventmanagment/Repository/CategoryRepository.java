package net.chiheb.eventmanagment.Repository;

import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @EntityGraph(attributePaths = {"events"})
    Category findAllByCategoryId(Long id);
}
