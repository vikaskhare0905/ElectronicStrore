package com.bikkadIT.electronicStore.services;

import com.bikkadIT.electronicStore.payloads.UserDto;

import java.util.List;


public interface UserService {

    //create user

    UserDto crateUser(UserDto userDto);

    //update

    UserDto updateUser(UserDto userDto, String userId);

    //delete

    void deleteUser(String userId);

    //get all users

    List<UserDto> getAllUser();

    //get single user by id

    UserDto getUserById(String userId);

    //get single user by email

    UserDto getUserByEmail(String email);

    //search user

    List<UserDto> searchUser(String keyword);

    //other user specific features

}
