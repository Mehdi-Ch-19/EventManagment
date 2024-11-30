package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // create new category by provinding name of the category
    @Transactional
    public Category createCategoty(Category category) {
        return categoryRepository.save(category);
    }
    // get a category by the primary key
    @Transactional
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    // get a catgory by name
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoriesByCategoryName(name);
    }
    // get all events filter by category
    @Transactional
    public Set<Event> getalleventsbycategory(Category cat){
        Category category = categoryRepository.findByCategoryId(cat.getCategoryId());
        return category.getEvents();
    }

}
