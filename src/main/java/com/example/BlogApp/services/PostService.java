package com.example.BlogApp.services;

import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto, Integer postId);

    //delete

    void deletePost(Integer postId);

    //get all posts

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user

    List<PostDto> getPostsByUser(Integer userId);

    //search
    List<PostDto> searchPosts(String keyword);
}
