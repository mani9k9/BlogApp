package com.example.BlogApp.repositories;

import com.example.BlogApp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {



}
