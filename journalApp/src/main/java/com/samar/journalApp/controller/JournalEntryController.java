package com.samar.journalApp.controller;

import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.service.JournalService;
import com.samar.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalService journalService;
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntries(){
        return journalService.getAllJournalEntries();
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry >getJournalEntryById(@PathVariable ObjectId myId){
        return journalService.getJournalEntryById(myId);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user=userService.getUserByUserName(userName).getBody();
        List<JournalEntry> all=user.getJournalEntries();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myId){
        return journalService.deleteJournalEntryById(myId);
    }
    @PutMapping("/id/{myId}")
    public ResponseEntity<String> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry entry){

        return journalService.updateJournalEntryById(myId,entry);
    }
    @PostMapping("/{userName}")
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName){
        try{

            return journalService.createJournalEntry(journalEntry,userName);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
