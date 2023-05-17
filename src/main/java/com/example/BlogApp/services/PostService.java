package com.example.BlogApp.services;

import com.example.BlogApp.entities.Post;
import com.example.BlogApp.payloads.PostDto;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

    //update

    Post updatePost(PostDto postDto, Integer postId);

    //delete

    void deletePost(Integer postId);

    //get all posts

    List<Post> getAllPost();

    //get single post
    Post getPostById(Integer postId);

    //get all post by category
    List<Post> getPostsByCategory(Integer categoryId);

    //get all post by user

    List<Post> getPostsByUser(Integer userId);

    //search
    List<Post> searchPosts(String keyword);
}
