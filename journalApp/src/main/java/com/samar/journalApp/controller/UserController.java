package com.samar.journalApp.controller;

import com.samar.journalApp.model.User;
import com.samar.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username){
        return userService.getUserByUserName(username);
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId){
        return userService.getUserById(myId);
    }
    @PutMapping("/username/{name}")
    public ResponseEntity<String> updateUser(@PathVariable String name,@RequestBody User user){
        return userService.updateUser(name,user);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<User> deleteById(@PathVariable ObjectId myId){
        return userService.deleteUserById(myId);
    }
}
