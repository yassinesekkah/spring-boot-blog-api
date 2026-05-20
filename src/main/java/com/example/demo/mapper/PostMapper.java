package com.example.demo.mapper;

import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    private final CategoryMapper categoryMapper;

    public PostMapper(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }
    //Entity -> Response
    public PostResponse toResponse (Post post){

        if(post == null){ return null;}

        //map category
        CategoryResponse categoryResponse = null;
        if(post.getCategory() != null){
            categoryResponse = categoryMapper.toResponse(post.getCategory());
        }
        //map user
        Long userId = post.getUser() != null ? post.getUser().getId() : null;
        String userName = post.getUser() != null ? post.getUser().getName() : null;

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                userId,
                userName,
                categoryResponse
        );
    }

    public List<PostResponse> toResponseList (List<Post> posts){

        if(posts == null || posts.isEmpty()){
            return List.of();
        }

        return posts.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
