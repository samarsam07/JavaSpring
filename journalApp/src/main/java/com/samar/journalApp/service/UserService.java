package com.samar.journalApp.service;


import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public ResponseEntity<String> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    public ResponseEntity<User> deleteUserById(ObjectId myId) {
        Optional<User> deleted=userRepository.findById(myId);
        userRepository.deleteById(myId);
        return new ResponseEntity<>(deleted.orElse(null),HttpStatus.OK);
    }
    public ResponseEntity<?> deleteByUserName(String username){
        userRepository.deleteByUserName(username);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    public ResponseEntity<User> getUserById(ObjectId myId) {
        return new ResponseEntity<>(userRepository.findById(myId).orElse(null),HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
    public ResponseEntity<?> saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return  new ResponseEntity<>("saved",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateUser(String name, User user) {
        User old=userRepository.findByUserName(name);
        if(old!=null){
            old.setUserName(user.getUserName());
            old.setPassword(passwordEncoder.encode(user.getPassword()));
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.save(old);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
    public void saveEntry(@RequestBody User user){
        userRepository.save(user);
    }

    public ResponseEntity<User> getUserByUserName(String username) {
        return new ResponseEntity<>(userRepository.findByUserName(username),HttpStatus.OK);
    }
}
