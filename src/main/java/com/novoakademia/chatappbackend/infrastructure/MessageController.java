package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.User.UserFacade;
import com.novoakademia.chatappbackend.message.Message;
import com.novoakademia.chatappbackend.message.MessageDto;
import com.novoakademia.chatappbackend.message.MessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageFacade messageFacade;

    private final UserFacade userFacade;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public MessageController(MessageFacade messageFacade, UserFacade userFacade) {
        this.messageFacade = messageFacade;
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        logger.info("Returning all messages!");
        List<Message> result = messageFacade.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/test")
    public String getUserFacadeString() {
        return userFacade.toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> getMessageById(@PathVariable String id) {
        logger.info("Returning message with id: " + id);
        Optional<Message> result = messageFacade.findById(id);
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/group/{id}")
    public ResponseEntity<List<MessageDto>> getMessagesByGroupId(@PathVariable String id) {
        logger.info("Returning messages for group with id: " + id);
        List<MessageDto> messageDtos = messageFacade.findMessagesByGroupId(id);
        return ResponseEntity.ok(messageDtos);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/delete/{id}")
    public ResponseEntity<MessageDto> deleteMessage(@PathVariable String id) {
        MessageDto result = messageFacade.deleteMessage(id);
        logger.info("Delted message with id: " + id);
        return ResponseEntity.ok(result);
    }




    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    @PostMapping
    public ResponseEntity<MessageDto> saveMessageAndSendViaWebSocket(@RequestBody MessageDto messageDto) {
        logger.info("Saving message...");
        MessageDto result = messageFacade.saveMessage(messageDto);
        URI uri = URI.create("/" + result.getMessageId());

        messagingTemplate.convertAndSend("/topic/message", result);


        return ResponseEntity.created(uri).body(result);
    }

}
