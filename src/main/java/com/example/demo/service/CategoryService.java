package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;



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

    





}
