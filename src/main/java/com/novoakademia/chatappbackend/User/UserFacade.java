package com.novoakademia.chatappbackend.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Transactional
public class UserFacade {
    private UserRepository repository;

    public UserFacade(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public User banOrUnbanUser(String id) {
        User userToBan = repository.findById(id).orElseThrow();
        userToBan.setBanned(!userToBan.isBanned());
        return userToBan;
    }
}
