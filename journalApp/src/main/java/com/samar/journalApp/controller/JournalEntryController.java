package com.samar.journalApp.controller;

import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.service.JournalService;
import com.samar.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalService journalService;
    @Autowired
    UserService userService;

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry >getJournalEntryById(@PathVariable ObjectId myId){
        return journalService.getJournalEntryById(myId);
    }
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userService.getUserByUserName(username).getBody();
        List<JournalEntry> all=user.getJournalEntries();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return journalService.deleteJournalEntryById(myId,username);
    }
    @PutMapping("/id/{myId}")
    public ResponseEntity<String> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry entry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return journalService.updateJournalEntryById(myId,entry,username);
    }
    @PostMapping
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry journalEntry){
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            return journalService.createJournalEntry(journalEntry,userName);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
