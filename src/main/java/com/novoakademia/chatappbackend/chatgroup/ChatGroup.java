package com.novoakademia.chatappbackend.chatgroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "chatGroups")
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "chatGroup")
    private List<Message> messages = new ArrayList<>();

    private boolean isPriv;

    public ChatGroup(String groupName) {
        this.groupName = groupName;
    }
}
