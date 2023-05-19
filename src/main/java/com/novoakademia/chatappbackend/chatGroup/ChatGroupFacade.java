package com.novoakademia.chatappbackend.chatGroup;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.sql.Array;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class ChatGroupFacade {
    private ChatGroupRepository repository;

    public ChatGroupFacade(ChatGroupRepository repository) {
        this.repository = repository;
    }

    public List<ChatGroup> findAll() {
        return repository.findAll();
    }

    public ChatGroup save(ChatGroup chatGroup) {
        return repository.save(chatGroup);
    }
}