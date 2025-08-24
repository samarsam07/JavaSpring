package com.samar.journalApp.service;

import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.JournalRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JournalService {
    private static final Logger log = LoggerFactory.getLogger(JournalService.class);
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserService userService;

    @Transactional
    public ResponseEntity<?> createJournalEntry(JournalEntry journalEntry, String userName) {
        User user=userService.getUserByUserName(userName).getBody();
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateJournalEntryById(ObjectId myId, JournalEntry newEntry,String userName) {
            User user=userService.getUserByUserName(userName).getBody();
            List<JournalEntry> collect=user.getJournalEntries()
                        .stream()
                        .filter(x->x.getId().equals(myId))
                        .collect(Collectors.toList());
            if(!collect.isEmpty()){
                Optional<JournalEntry> entry=journalRepository.findById(myId);
                if(entry.isPresent()){
                   JournalEntry old=entry.get();
                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                    journalRepository.save(old);
                    return new ResponseEntity<>("Success", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Failed",HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<?> deleteJournalEntryById(ObjectId myId,String userName) {
        try {
            User user=userService.getUserByUserName(userName).getBody();
            Optional<JournalEntry> deleted=journalRepository.findById(myId);
            boolean removed=user.getJournalEntries().removeIf(x->x.getId().equals(myId));
            if(removed){
                userService.saveEntry(user);
                journalRepository.deleteById(myId);
            }
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        } catch (Exception e) {
            log.error("kuch fat gaya h {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<JournalEntry> getJournalEntryById(ObjectId myId) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        User user=userService.getUserByUserName(name).getBody();
        List<JournalEntry> collect=user.getJournalEntries()
                            .stream()
                            .filter(x->x.getId().equals(myId))
                            .collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalRepository.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<JournalEntry>> getAllJournalEntries() {
        return new ResponseEntity<>(journalRepository.findAll(),HttpStatus.OK);
    }
}
