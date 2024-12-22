package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.mapper.CategoryMapper;
import net.chiheb.eventmanagment.Entity.Category;
import net.chiheb.eventmanagment.Service.CategoryService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCategorys(){
        List<Category> categories = categoryService.getAllCategory();
        return ResponceHandler.generateResponse("categories", HttpStatus.OK,
                categories.stream().map(categoryMapper::todto));
    }
}
