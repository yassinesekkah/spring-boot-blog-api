package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponse create(CategoryRequest request){
        log.info("Creating new category with name: {}", request.getName());

        if(categoryRepository.existsByName(request.getName())){
            log.warn("Category creation failed - name already exists: {}", request.getName());
            throw new RuntimeException(
                    "Category with name '" + request.getName() + "' already exists"
            );
        }

        //2- Map to entity
        Category category = categoryMapper.toEntity(request);

        //3-save
        Category saved = categoryRepository.save(category);

        log.info("Category created successfully with id: {}", saved.getId());
        return categoryMapper.toResponse(saved);
    }


    public List<CategoryResponse> findAll(){

        log.debug("fetchin all categories");

        List<Category> categories = categoryRepository.findAll();
        log.info("Found {} categories", categories.size());

        return categoryMapper.toResponseList(categories);
    }

    public CategoryResponse findById(Long id){

        log.debug("Fetching a category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Category not found with id: {}", id);
                    return new EntityNotFoundException("Category not found with id: " + id);
                });

        return categoryMapper.toResponse(category);
    }

    public CategoryResponse update(Long id, CategoryRequest request){

        log.debug("Updating name a category with id: {}", id);

        //1-find existing category
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->{
                    log.warn("Category not found with id: {}", id);
                    return new EntityNotFoundException("Category not found with id: " + id);
                });

        // 2-check duplicate
        if(!category.getName().equals(request.getName())
            && categoryRepository.existsByName(request.getName())){

            log.warn("Update failed - name already exists : {}", request.getName());

            throw new RuntimeException("Category with name '" + request.getName() + "' already exists");
        }

        // 3-update field
        category.setName(request.getName());

        // 4- save
        Category updated = categoryRepository.save(category);
        log.info("Category updated with id : {}", updated.getId());

        // 5- return response
        return categoryMapper.toResponse(updated);

    }


}
