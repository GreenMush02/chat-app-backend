package com.novoakademia.chatappbackend.message;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserRepository;
import com.novoakademia.chatappbackend.chatgroup.ChatGroup;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MessageFacade {

    private final Logger logger = LoggerFactory.getLogger(MessageFacade.class);
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatGroupRepository chatGroupRepository;


    public MessageFacade(MessageRepository messageRepository, UserRepository userRepository, ChatGroupRepository chatGroupRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatGroupRepository = chatGroupRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = new Message(messageDto);
        return messageRepository.save(message).dto();
    }

    public List<MessageDto> findMessagesByGroupId(String id) {
        List<Message> messages = messageRepository.findMessagesByGroupId(id);
        return messages
                .stream()
                .map(Message::dto)
                .toList();
    }
}
