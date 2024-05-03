package org.reelskill.controllers;

import org.reelskill.domain.Result;
import org.reelskill.domain.UserService;
import org.reelskill.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        User user = service.findById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestParam String username, @RequestParam String password) {
        User user = service.authenticateUser(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{username}")
    public ResponseEntity<String> getUserEmailAddress(@PathVariable String username) {
        String emailAddress = service.getUserEmailAddress(username);
        if (emailAddress == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emailAddress);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Result<User> result = service.createUser(user);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
