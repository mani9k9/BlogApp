package com.example.BlogApp.services.impl;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.Post;
import com.example.BlogApp.entities.User;
import com.example.BlogApp.exception.ResourceNotFoundException;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.repositories.CategoryRepo;
import com.example.BlogApp.repositories.PostRepo;
import com.example.BlogApp.repositories.UserRepo;
import com.example.BlogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Not Found",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","Category not Found",categoryId));

       Post post =  this.modelMapper.map(postDto,Post.class);
       post.setImageName("default.pmg");
       post.setAddedDate(new Date());
       post.setUser(user);
       post.setCategory(category);

       Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {



        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
