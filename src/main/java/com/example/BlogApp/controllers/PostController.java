package com.example.BlogApp.controllers;

import com.example.BlogApp.config.AppConstant;
import com.example.BlogApp.entities.Post;
import com.example.BlogApp.payloads.ApiResponse;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.payloads.PostResponse;
import com.example.BlogApp.services.FileService;
import com.example.BlogApp.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private  String path;
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
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false)  Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
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
@GetMapping("/posts/search/{keywords}")
//Search
public ResponseEntity<List<PostDto>> searchPostByTitle(
        @PathVariable("keywords") String keywords
){
    List<PostDto> result = this.postService.searchPosts(keywords);
    return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
}

//post image upload
    @PostMapping("/post/image/upload/{postId}")
    public  ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
        String fileName = this.fileService.uploadImage(path, image);
        PostDto postDto  = this.postService.getPostById(postId);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
}
