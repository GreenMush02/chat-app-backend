package com.novoakademia.chatappbackend.chatgroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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

    public Optional<ChatGroup> findById(String id) {
        return repository.findById(id);
    }

    public ChatGroupDto createChatGroup(ChatGroupDto chatGroupDto) {
        ChatGroup chatGroup = new ChatGroup(chatGroupDto);
        return repository.save(chatGroup).dto();
    }
}