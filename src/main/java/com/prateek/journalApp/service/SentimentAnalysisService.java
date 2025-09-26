package com.prateek.journalApp.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public String getSentimentByEntry(String text) {
        return "Happy";
    }
}
