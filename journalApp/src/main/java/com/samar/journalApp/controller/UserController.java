package com.samar.journalApp.controller;

import com.samar.journalApp.model.User;
import com.samar.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers(){
//        return userService.getAllUsers();
//    }
    @GetMapping
    public ResponseEntity<User> getUserByUserName(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return userService.getUserByUserName(username);
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId){
        return userService.getUserById(myId);
    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        return userService.updateUser(name,user);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteByUserName(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return userService.deleteByUserName(username);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<User> deleteById(@PathVariable ObjectId myId){
        return userService.deleteUserById(myId);
    }
}
