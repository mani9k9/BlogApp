package com.example.BlogApp.controllers;

import com.example.BlogApp.entities.Post;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
         PostDto createPost =this.postService.createPost(postDto,userId,categoryId);
         return  new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }
}
