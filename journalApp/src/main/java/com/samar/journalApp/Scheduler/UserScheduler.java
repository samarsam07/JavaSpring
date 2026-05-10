package com.samar.journalApp.Scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.samar.journalApp.enums.Sentiment;
import com.samar.journalApp.model.JournalEntry;
import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.UserRepositoryImpl;
import com.samar.journalApp.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserScheduler {

	private static final Logger log = LoggerFactory.getLogger(UserScheduler.class);
	@Autowired
	private UserRepositoryImpl userRepository;
	@Autowired
	private EmailService emailService;

	@Scheduled(cron = "0 * * ? * *")
	public void fetchUserAndSaMail() {
		List<User> users = userRepository.getUserForSA();
		for (User user : users) {
			List<JournalEntry> journalEntries = user.getJournalEntries();
			List<Sentiment> filtered = journalEntries.stream()
					.filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(30, ChronoUnit.DAYS)))
					.map(x -> x.getSentiment()).collect(Collectors.toList());
			Map<Sentiment, Integer> sentimentCount = new HashMap<>();
			for (Sentiment sentiment : filtered) {
				if (sentiment != null)
					sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
			}
			Sentiment mostFreq = null;
			int max = 0;
			for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
				if (entry.getValue() > max) {
					max = entry.getValue();
					mostFreq = entry.getKey();
				}
			}
			if (mostFreq != null) {
				emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFreq.toString());
				log.info("send");
			}

		}
	}
}
