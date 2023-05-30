package com.novoakademia.chatappbackend.infrastructure;

import com.novoakademia.chatappbackend.chatgroup.ChatGroup;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupDto;
import com.novoakademia.chatappbackend.chatgroup.ChatGroupFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/groups")
public class ChatGroupController {
    private final Logger logger = LoggerFactory.getLogger(ChatGroupController.class);

    private final ChatGroupFacade facade;

    public ChatGroupController(final ChatGroupFacade facade) {
        this.facade = facade;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<ChatGroup>> getAllGroups() {
        logger.info("Returning all ChatGroups!");
        List<ChatGroup> result = facade.findAll();
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ChatGroup>> getGroupById(@PathVariable String id) {
        logger.info("Returning group with id: " + id);
        Optional<ChatGroup> result = facade.findById(id);
        return ResponseEntity.ok(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<ChatGroupDto> createGroup(@RequestBody ChatGroupDto chatGroupDto) {
        logger.info("Creating group : " + chatGroupDto.getGroupName());
        ChatGroupDto result = facade.createChatGroup(chatGroupDto);
        URI uri = URI.create("/" + result.getGroupId());

        return ResponseEntity.created(uri).body(result);
    }
}
