package com.example.demo.service;

import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public PostResponse createPost(PostRequest request){

        //get user
        User user =  userService.getUserById(request.getUserId());

        //creation de post
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        //link with user
        post.setUser(user);

        //save
        Post savedPost = postRepository.save(post);

        //mapping
        return new PostResponse(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getUser().getId(),
                savedPost.getUser().getName()
        );
    }

    public Page<PostResponse> getPosts(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts = postRepository.findAll(pageable);

       return posts.map(post ->
               new PostResponse(
                       post.getId(),
                       post.getTitle(),
                       post.getContent(),
                       post.getUser().getId(),
                       post.getUser().getName()
               )
               );
    }

    private Post getPostEntityById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
    }

    public PostResponse getPostById(Long id){

        Post post = getPostEntityById(id);

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getName()
        );
    }

    public PostResponse updatePost(PostRequest request, Long id){

        Post post = getPostEntityById(id);

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        if(request.getUserId() != null){
            User user = userService.getUserById(request.getUserId());
            post.setUser(user);
        }

        Post updatedPost = postRepository.save(post);

        return new PostResponse(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getUser().getId(),
                updatedPost.getUser().getName()
        );
    }

    public void deletePost(Long id){
        Post post = getPostEntityById(id);
        postRepository.delete(post);
    }

    public List<PostResponse> getPostByUserId(Long userId){

        //ila makanch ghadi yraje3 404
        userService.getUserById(userId);

        List<Post> posts = postRepository.findPostByUserId(userId);

        return posts.stream().map(post ->
                new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getId(),
                        post.getUser().getName()
                )
                ).toList();

    }

}
