package com.example.demo.controller;

import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request){
        return postService.createPost(request);
    }

    @GetMapping
    public List<PostResponse> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("{id}")
    public PostResponse getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PutMapping("{id}")
    public PostResponse updatePost(@RequestBody PostRequest request, @PathVariable Long id){
        return postService.updatePost(request, id);
    }


}
