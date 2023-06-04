package com.bikkadIT.electronicStore.controllers;

import com.bikkadIT.electronicStore.helper.ApiResponse;
import com.bikkadIT.electronicStore.helper.AppConstant;
import com.bikkadIT.electronicStore.payloads.UserDto;
import com.bikkadIT.electronicStore.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author Vikas Khare
     * @apiNote Api to create user
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Request for Starting service layer to create the user");
        UserDto userdto = this.userService.crateUser(userDto);
        log.info("Request completed of service layer to create the user");
        return new ResponseEntity<>(userdto, HttpStatus.CREATED);
    }


    /**
     * @author Vikas Khare
     * @apiNote Api to get user by userId
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        log.info("Request Starting for service layer to get the user by userId : {}", userId);
        UserDto userdto = this.userService.getUserById(userId);
        log.info("Request completed for service layer to get the user by userId : {}", userId);
        return new ResponseEntity<>(userdto, HttpStatus.FOUND);

    }

    /**
     * @author Vikas Khare
     * @apiNote Api to get All users
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Request Starting for service layer to get All users");
        List<UserDto> list = this.userService.getAllUser();
        log.info("Request completed for service layer to get All users");
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    /**
     * @author Vikas Khare
     * @apiNote Api to update the user by userId
     * @param userDto
     * @param userId
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        log.info("Request Starting for service layer to update the user by userId : {}", userId);
        UserDto userdto = this.userService.updateUser(userDto, userId);
        log.info("Request completed for service layer to update the user by userId : {}", userId);
        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    /**
     * @author Vikas Khare
     * @apiNote Api to delete the user by userId
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        log.info("Request Starting for service layer to delete the user by userId : {}", userId);
        this.userService.deleteUser(userId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message(AppConstant.USER_DELETE)
                .success(true)
                .Status(HttpStatus.OK)
                .build();
        log.info("Request completed for service layer to delete the user by userId : {}", userId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }


    /**
     * @author Vikas Khare
     * @apiNote Api to get user by  user email
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getuserbyEmail(@PathVariable String email) {
        log.info("Request Starting for service layer to get the user by email ;{}", email);
        UserDto userdto = this.userService.getUserByEmail(email);
        log.info("Request completed for service layer to get the user by email ;{}", email);
        return new ResponseEntity<>(userdto, HttpStatus.OK);

    }

    /**
     * @author Vikas Khare
     * @apiNote Api is used to search User by some keyword
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        log.info("Request Starting for service layer to search the user with keyword :{}", keyword);
        List<UserDto> userdto = this.userService.searchUser(keyword);
        log.info("Request completed for service layer to search the user with keyword :{}", keyword);
        return new ResponseEntity<>(userdto, HttpStatus.FOUND);

    }

}