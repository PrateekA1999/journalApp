package com.prateek.journalApp.service;

import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    public void createEntry(User newEntry) {
        newEntry.setPassword(passwordEncoder.encode(newEntry.getPassword()));
        newEntry.setRoles(List.of("USER"));
        userRepository.save(newEntry);
    }

    public void createAdminEntry(User newEntry) {
        newEntry.setPassword(passwordEncoder.encode(newEntry.getPassword()));
        newEntry.setRoles(List.of("USER", "ADMIN"));
        userRepository.save(newEntry);
    }

    public void updateEntry(User existingEntry) {
        userRepository.save(existingEntry);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
