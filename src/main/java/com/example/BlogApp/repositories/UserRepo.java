package com.example.BlogApp.repositories;

import com.example.BlogApp.entities.User;
import com.example.BlogApp.payloads.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
