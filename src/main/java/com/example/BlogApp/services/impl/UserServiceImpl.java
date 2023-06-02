package com.example.BlogApp.services.impl;

import com.example.BlogApp.config.AppConstant;
import com.example.BlogApp.entities.*;
import com.example.BlogApp.exception.ResourceNotFoundException;
import com.example.BlogApp.payloads.UserDto;
import com.example.BlogApp.repositories.*;
import com.example.BlogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
@Autowired
    private ModelMapper modelMapper;
@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        //encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        //roles
        Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);

        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser =this.userRepo.save(user);
        return this.usertoDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(user.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.usertoDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
         this.userRepo.delete(user);
    }

    @Override
    public UserDto usertoDto(User savedUser) {
        return null;
    }


    public User dtoToUser(UserDto userDto){
        User  user =this.modelMapper.map(userDto,User.class);


       /* user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());*/
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto= this.modelMapper.map(user, UserDto.class);



       /* userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());*/
        return userDto;
    }
}
