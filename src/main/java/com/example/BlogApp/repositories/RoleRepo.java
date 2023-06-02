package com.example.BlogApp.repositories;

import com.example.BlogApp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
