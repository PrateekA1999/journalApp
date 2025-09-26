package com.prateek.journalApp.scheduler;

import com.prateek.journalApp.cache.AppCache;
import com.prateek.journalApp.entity.JournalEntry;
import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.enums.Sentiment;
import com.prateek.journalApp.repository.UserRepositoryImpl;
import com.prateek.journalApp.service.EmailService;
import com.prateek.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 0 * * */7")
    public void fetchUsersAndSendSAEmail() {
        List<User> users = userRepository.getUsersForSA();
        for (User user : users) {
            List<Sentiment> sentiments = user.getJournalEntries().stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment)
                    .toList();
            Map<Sentiment, Integer> sentimentCount = new EnumMap<>(Sentiment.class);
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> e : sentimentCount.entrySet()) {
                if (e.getValue() > maxCount) {
                    maxCount = e.getValue();
                    mostFrequentSentiment = e.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment Analysis", "Your sentiment analysis for the last 7 days is: " + mostFrequentSentiment);
            }
        }
    }

    //@Scheduled(cron = "*/10 * * * * *")
    public void clearAppCache() {
        appCache.init();
    }
}
