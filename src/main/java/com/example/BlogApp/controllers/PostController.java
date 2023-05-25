package com.example.BlogApp.controllers;

import com.example.BlogApp.payloads.ApiResponse;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.payloads.PostResponse;
import com.example.BlogApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
         PostDto createPost =this.postService.createPost(postDto,userId,categoryId);
         return  new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }


    //getbyuser
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(
            @PathVariable Integer userId
    ){

        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //getbycategory

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId
    ){

        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //Get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)  Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
       PostDto postDto =this.postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //delete

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post successfully deleted!!", true);
    }



    //updATE
@PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto  ,@PathVariable Integer postId){
    PostDto updatePost = this.postService.updatePost(postDto, postId);
    return  new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
}
}
