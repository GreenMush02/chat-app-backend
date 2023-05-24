package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.message.Message;
import com.novoakademia.chatappbackend.message.MessageDto;
import com.novoakademia.chatappbackend.message.MessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageFacade messageFacade;

    public MessageController(MessageFacade messageFacade) {
        this.messageFacade = messageFacade;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        logger.info("Returning all messages!");
        List<Message> result = messageFacade.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable String id) {
        logger.info("Returning message with id: " + id);
        Optional<Message> result = messageFacade.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<MessageDto>> getMessagesByGroupId(@PathVariable String id) {
        logger.info("Returning messages for group with id: " + id);
        List<MessageDto> messageDtos = messageFacade.findMessagesByGroupId(id);
        return ResponseEntity.ok(messageDtos);
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        logger.info("Creating message: " + messageDto.getMessageId());
        MessageDto result = messageFacade.saveMessage(messageDto);
        URI uri = URI.create("/" + result.getMessageId());
        return ResponseEntity.created(uri).body(result);
    }

}
