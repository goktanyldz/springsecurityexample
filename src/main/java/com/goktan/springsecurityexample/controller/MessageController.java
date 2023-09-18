package com.goktan.springsecurityexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()

public class MessageController {

    @GetMapping("/index")
    public ResponseEntity<String> messageAllUsers(){
        return ResponseEntity.ok("Hello all users");
    }


    @GetMapping("/admin")
    public ResponseEntity<String> messageAdmin(){
        return ResponseEntity.ok("Hello admin");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> messageRegistered(){
        return ResponseEntity.ok("Hello registered users");
    }

}
