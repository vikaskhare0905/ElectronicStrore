package com.bikkadIT.electronicStore.services.impl;

import com.bikkadIT.electronicStore.exceptions.ResourceNotFoundException;
import com.bikkadIT.electronicStore.helper.AppConstant;
import com.bikkadIT.electronicStore.payloads.UserDto;
import com.bikkadIT.electronicStore.entities.User;
import com.bikkadIT.electronicStore.repositories.UserRepository;
import com.bikkadIT.electronicStore.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto crateUser(UserDto userDto) {
        log.info("Request started to create user");
        User user = this.mapper.map(userDto, User.class);
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        user.setImageName("Vikas.png");

        User savedUser = this.userRepository.save(user);
        log.info("Request completed to create user");
        return this.mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        log.info("Request for starting the update of user by userId : {}", userId) ;
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getAbout());
        user.setAbout(userDto.getAbout());
        user.setImageName(userDto.getImageName());

        User user1 = this.userRepository.save(user);
        log.info("Request for complete the update of user by userId : {}", userId);
        return this.mapper.map(user1,UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Request for starting deletion of user by userId : {}", userId);
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found Exception"));
        log.info("Request for completed deletion of user by userId : {}", userId);
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        log.info("Request starting to get all users");
        List<User> list = this.userRepository.findAll();
        List<UserDto> userdata = list.stream().map(data-> this.mapper.map(data, UserDto.class).collect(Collectors.toList()))
        log.info("Request completed to get all users");
        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        log.info("Request starting to get the User by userId : {}", userId);
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        log.info("Request completed to get the User by userId : {}", userId);
        return this.mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Request for starting get user by mailId : {}", email);
        User user = this.userRepository.findByEmail().orElseThrow(()->new ResourceNotFoundException(AppConstant.EXCEPTION_MSG));
        log.info("Request for completed get user by mailId : {}", email);
        return this.mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        log.info("Request starting to search the user");
        List<User>  users = this.userRepository.findByNameContaining(keyword);
        log.info("Request completed to search the user");
        return this.mapper.map(users, UserDto.class);
    }
}
