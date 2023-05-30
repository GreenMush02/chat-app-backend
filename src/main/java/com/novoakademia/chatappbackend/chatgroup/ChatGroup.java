package com.novoakademia.chatappbackend.chatgroup;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.message.Message;
import com.novoakademia.chatappbackend.message.MessageDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
@Entity
@Table(name = "chat_groups")
public class ChatGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String groupId;

    private String groupName;

    @CollectionTable(name = "user_chat_groups", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "user_id")
    @ElementCollection
    private List<String> users = new ArrayList<>();

    @OneToMany(mappedBy = "chatGroupId")
    private List<Message> messages = new ArrayList<>();

    private boolean isPriv;
    private String queueId;

    public ChatGroup(String groupName) {
        this.groupName = groupName;
    }

    public ChatGroup(ChatGroupDto chatGroupDto) {
        this.groupName = chatGroupDto.getGroupName();
        this.users = chatGroupDto.getUsers();
        this.messages = chatGroupDto.getMessages();
        this.isPriv = chatGroupDto.isPriv();
        this.queueId = "queue." + chatGroupDto.getGroupName();
    }

    public ChatGroupDto dto() {
        return new ChatGroupDto(
                this.groupId,
                this.groupName,
                this.users,
                this.messages,
                this.isPriv,
                this.queueId
        );
    }
}
