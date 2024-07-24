package pl.meksu.campaignrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meksu.campaignrud.model.User;
import pl.meksu.campaignrud.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/funds/{id}")
    public ResponseEntity<Double> getUserFunds(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user.getFunds());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (!userService.isMailUnique(user.getMail())) {
            return ResponseEntity.badRequest().body("User with this e-mail exists.");
        }
        User registeredUser = userService.createUser(user);
        return ResponseEntity.ok("User registered successfully: " + registeredUser.getMail());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.loginUser(loginRequest.getMail(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid e-mail or password.");
        }
        return ResponseEntity.ok(user.getId());
    }
}