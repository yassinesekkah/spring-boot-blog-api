package com.example.demo.service;

import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
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

    public List<PostResponse> getPosts(){
       List<Post> posts = postRepository.findAll();

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

    public PostResponse getPostById(Long id){

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getName()
        );
    }

}
