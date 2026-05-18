package com.example.demo.mapper;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request){

        if(request == null){
            return null;
        }

        Category category = new Category();
        category.setName(request.getName());

        return category;
    }

    public CategoryResponse toResponse(Category category){

        if(category == null){
            return null;
        }

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setCreatedAt(category.getCreatedAt());

        return response;
    }

    public List<CategoryResponse> toResponseList(List<Category> categories){

        if(categories == null || categories.isEmpty()){
            return List.of(); //list khawia
        }

        return categories.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
