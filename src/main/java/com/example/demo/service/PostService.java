package com.example.demo.service;

import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, UserService userService,
                       CategoryService categoryService, PostMapper postMapper){
        this.postRepository = postRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.postMapper = postMapper;
    }

    public PostResponse createPost(PostRequest request){

        //get user
        User user =  userService.getUserById(request.getUserId());

        //get categories
        Category category = categoryService.getCategoryById(request.getCategoryId());

        //creation de post
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        //link with user
        post.setUser(user);
        //lik with category
        post.setCategory(category);

        //save
        Post savedPost = postRepository.save(post);

        //mapping
        return postMapper.toResponse(savedPost);
    }

    public Page<PostResponse> getPosts(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts = postRepository.findAll(pageable);

       return posts.map(postMapper::toResponse);
    }

    private Post getPostEntityById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public PostResponse getPostById(Long id){

        Post post = getPostEntityById(id);

        return postMapper.toResponse(post);
    }

    public PostResponse updatePost(PostRequest request, Long id){

        Post post = getPostEntityById(id);

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        //user update
        if(request.getUserId() != null){
            User user = userService.getUserById(request.getUserId());
            post.setUser(user);
        }

        //category update
        if(request.getCategoryId() != null){
            Category category = categoryService.getCategoryById(request.getCategoryId());
            post.setCategory(category);
        }

        Post updatedPost = postRepository.save(post);

        return postMapper.toResponse(updatedPost);
    }

    public void deletePost(Long id){
        Post post = getPostEntityById(id);
        postRepository.delete(post);
    }

    public List<PostResponse> getPostByUserId(Long userId){

        //ila makanch ghadi yraje3 404
        userService.getUserById(userId);

        List<Post> posts = postRepository.findPostByUserId(userId);

        return postMapper.toResponseList(posts);

    }

}
