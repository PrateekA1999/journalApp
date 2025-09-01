package com.prateek.journalApp.cache;

import com.prateek.journalApp.entity.ConfigJournalApp;
import com.prateek.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String, String> APP_CACHE;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init() {
        APP_CACHE = new HashMap<>();
        List<ConfigJournalApp> configJournalAppList = configJournalAppRepository.findAll();
        configJournalAppList.forEach(configJournalApp -> APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue()));
    }

    public enum keys {
        WEATHER_API_URL,
    }
}
