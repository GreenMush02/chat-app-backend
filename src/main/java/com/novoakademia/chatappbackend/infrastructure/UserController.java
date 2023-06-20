package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserFacade;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserFacade facade;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UserController(final UserFacade facade) { this.facade = facade;}

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Returning all users!");
        List<User> result = facade.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/test")
    public String getUserFacadeString() {
        return facade.toString();
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
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        logger.info("Creating user: " + user.getUserName());
        User result = facade.save(user);
        URI uri = URI.create("/" + result.getUserId());

        messagingTemplate.convertAndSend("/topic/user", result);

        return ResponseEntity.created(uri).body(result);

    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/ban/{id}")
    public ResponseEntity<User> banOrUnbanUser(@PathVariable String id) {
        User result = facade.banOrUnbanUser(id);

        messagingTemplate.convertAndSend("/topic/user", result);

        if(result.isBanned()) {
            logger.info("Banned " + result.getUserName());
        } else {
            logger.info("Unbanned " + result.getUserName());
        }
        return ResponseEntity.ok(result);
    }

}
