package com.restAPI.Task.controller;

import com.restAPI.Task.entity.User;
import com.restAPI.Task.repository.UserRepo;
import com.restAPI.Task.request.AddUserRequest;
import com.restAPI.Task.request.ChangeUserStatusRequest;
import com.restAPI.Task.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserManagementController {

    @Autowired
    private UserRepo userRepo;

    //Add new user
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest addUserRequest) {

        User user = new User();
        user.setName(addUserRequest.getName());
        user.setEmail(addUserRequest.getEmail());
        user.setGender(addUserRequest.getGender());
        user.setPhone(addUserRequest.getPhone());
        user.setDateOfBirth(addUserRequest.getDateOfBirth());
        user.setStatus(addUserRequest.getStatus());
        user.setPassword(addUserRequest.getPassword());
        userRepo.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    //Ability to Change User Status (Active, In Active, Deleted, Expired
    @RequestMapping(path = "/users/change", method = RequestMethod.PUT)
    public ResponseEntity<String> changeStatus(@RequestBody ChangeUserStatusRequest changeUserStatusRequest) {

        Optional<User> optionalUser = userRepo.findById(changeUserStatusRequest.getId());
        if (optionalUser.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        User user = optionalUser.get();
        user.setStatus(changeUserStatusRequest.getStatus());
        userRepo.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete one or more user on the same time
    @RequestMapping(path = "/users/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUsers(@RequestBody List<Long> ids) {
        ids.forEach(id -> userRepo.deleteById(id));

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    //Ability to Login for Registered Users
    @RequestMapping(path = "/users/login", method = RequestMethod.POST)
    public ResponseEntity<String> userLogin(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepo.findByEmail(email);

        if (user == null)
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);

        if (!user.getPassword().equals(password))
            return new ResponseEntity<>("password error", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    //view of all users saved on the database
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/users/photo/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> uploadUserPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
        try {
            Optional<User> optionalUser = userRepo.findById(id);
            if (optionalUser.isEmpty())
                return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);

            User user = optionalUser.get();
            user.setPhoto(photo.getBytes());

            userRepo.save(user);

            return new ResponseEntity<>("photo accepted",HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("photo fail to upload",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
