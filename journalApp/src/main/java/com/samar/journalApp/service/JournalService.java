package com.samar.journalApp.service;

import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserService userService;

    public ResponseEntity<?> createJournalEntry(JournalEntry journalEntry, String userName) {
        User user=userService.getUserByUserName(userName).getBody();
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateJournalEntryById(ObjectId myId, JournalEntry journalEntry) {
            JournalEntry old=journalRepository.findById(myId).orElse(null);
            if(old!=null) {
                old.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().equals("") ? journalEntry.getTitle() : old.getTitle());
                old.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : old.getContent());
            }else {
                return new ResponseEntity<>("Failed",HttpStatus.NOT_FOUND);
            }
            journalRepository.save(old);
            return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<JournalEntry> deleteJournalEntryById(ObjectId myId) {
        Optional<JournalEntry> deleted=journalRepository.findById(myId);
        journalRepository.deleteById(myId);
        return new ResponseEntity<>(deleted.orElse(null),HttpStatus.OK);
    }

    public ResponseEntity<JournalEntry> getJournalEntryById(ObjectId myId) {
        return new ResponseEntity<>(journalRepository.findById(myId).orElse(null),HttpStatus.OK);
    }

    public ResponseEntity<List<JournalEntry>> getAllJournalEntries() {
        return new ResponseEntity<>(journalRepository.findAll(),HttpStatus.OK);
    }
}
