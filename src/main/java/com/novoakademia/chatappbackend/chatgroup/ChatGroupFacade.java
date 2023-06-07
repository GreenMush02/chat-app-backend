package com.novoakademia.chatappbackend.chatgroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class ChatGroupFacade {
    private ChatGroupRepository repository;

    @Autowired
    private RabbitAdmin rabbitAdmin;


    public ChatGroupFacade(ChatGroupRepository repository) {
        this.repository = repository;
    }

    public List<ChatGroup> findAll() {
        return repository.findAll();
    }

    public ChatGroup save(ChatGroup chatGroup) {
        return repository.save(chatGroup);
    }

    public ChatGroupDto findById(String id) {
        return repository.findById(id).orElseThrow().dto();
    }

    public ChatGroupDto createChatGroup(ChatGroupDto chatGroupDto) {
        ChatGroup chatGroup = new ChatGroup(chatGroupDto);
        ChatGroupDto result = repository.save(chatGroup).dto();
        Queue queue = new Queue(result.getQueueId(), true, false, false);
        rabbitAdmin.declareQueue(queue);
        return result;
    }
}