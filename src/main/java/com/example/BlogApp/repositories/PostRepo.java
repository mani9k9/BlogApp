package com.example.BlogApp.repositories;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.Post;
import com.example.BlogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
