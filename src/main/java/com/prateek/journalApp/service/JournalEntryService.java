package com.prateek.journalApp.service;

import com.prateek.journalApp.entity.JournalEntry;
import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void createEntry(JournalEntry newEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            newEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(newEntry);
            user.getJournalEntries().add(save);
            userService.updateEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while creating the entry.",e);
        }
    }

    public void createEntry(JournalEntry newEntry) {
        journalEntryRepository.save(newEntry);
    }


    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            if (removed) {
                userService.updateEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.",e);
        }
        return removed;
    }
}
