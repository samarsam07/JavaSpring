package com.samar.journalApp.Scheduler;


import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.UserRepositoryImpl;
import com.samar.journalApp.service.EmailService;
import com.samar.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SentimentAnalysisService SaService;
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSaMail(){
        List<User> users=userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries=user.getJournalEntries();
            List<String> filtered=journalEntries.stream()
                    .filter(
                            x -> x.getDate()
                                    .isAfter(LocalDateTime.now()
                                            .minus(7, ChronoUnit.DAYS)))
                    .map(x->x.getContent())
                    .collect(Collectors.toList());
            String entry=String.join("",filtered);
//           String sentiment= SaService.getSentiment(entry);
           emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",entry);
        }
    }
}
