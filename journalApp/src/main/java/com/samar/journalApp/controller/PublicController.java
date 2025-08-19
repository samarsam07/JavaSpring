package com.samar.journalApp.controller;

import com.samar.journalApp.model.User;
import com.samar.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;
    @GetMapping("/check")
    public String check(){
        return "hello";
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.saveNewUser(user);
    }

}
