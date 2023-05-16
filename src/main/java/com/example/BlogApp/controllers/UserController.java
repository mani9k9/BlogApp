package com.example.BlogApp.controllers;

import com.example.BlogApp.payloads.ApiResponse;
import com.example.BlogApp.payloads.UserDto;
import com.example.BlogApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
@PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //put update user
    @PostMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
       UserDto updateUser  =this.userService.updateUser(userDto, uid);
       return ResponseEntity.ok(updateUser);
    }

    //Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){

    this.userService.deleteUser(uid);
    return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);

    }

    // Get
@GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
