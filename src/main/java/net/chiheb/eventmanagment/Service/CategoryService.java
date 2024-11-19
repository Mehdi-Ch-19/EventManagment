package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    public Category createCategoty(Category category) {
        return categoryRepository.save(category);
    }
    @Transactional
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @jakarta.transaction.Transactional
    public List<Event> getalleventsbycategory(Long id){
        Category category = categoryRepository.findAllByCategoryId(id);
        return category.getEvents();
    }

}
