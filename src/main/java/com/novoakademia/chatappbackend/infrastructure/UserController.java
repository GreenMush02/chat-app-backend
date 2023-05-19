package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserFacade facade;

    public UserController(final UserFacade facade) { this.facade = facade;}

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Returning all users!");
        List<User> result = facade.findAll();
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable String id) {
        logger.info("Returning user with id: " + id);
        Optional<User> result = facade.findById(id);
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        logger.info("Creating user: " + user.getUserName());
        User result = facade.save(user);
        URI uri = URI.create("/" + result.getUserId());
        return ResponseEntity.created(uri).body(result);
    }

}
