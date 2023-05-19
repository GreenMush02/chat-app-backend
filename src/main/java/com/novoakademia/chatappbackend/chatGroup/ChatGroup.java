package com.novoakademia.chatappbackend.chatGroup;

import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.message.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_groups")
public class ChatGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String groupId;

    private String groupName;

    @ManyToMany(mappedBy = "chatGroups")
    private List<User> users;

    @OneToMany(mappedBy = "chatGroup")
    private List<Message> messages;
}
