package com.prateek.journalApp.controller;

import com.prateek.journalApp.entity.User;
import com.prateek.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        boolean created = userService.createEntry(user);
        if(created){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(Map.of("message", "Error Occurred"), HttpStatus.BAD_REQUEST);
    }
}
