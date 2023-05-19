package com.novoakademia.chatappbackend.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
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
}
