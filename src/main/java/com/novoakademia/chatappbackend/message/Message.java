package com.novoakademia.chatappbackend.message;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.novoakademia.chatappbackend.User.User;
import com.novoakademia.chatappbackend.User.UserDto;
import com.novoakademia.chatappbackend.chatgroup.ChatGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String messageId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("authorId")
    private User author;
    private LocalDateTime time;
    private String content;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("chatGroupId")
    private ChatGroup chatGroup;

    public Message(User author, LocalDateTime time, String content, boolean isDeleted, ChatGroup chatGroup) {
        this.author = author;
        this.time = time;
        this.content = content;
        this.isDeleted = isDeleted;
        this.chatGroup = chatGroup;
    }

    public MessageDto dto() {
        return new MessageDto(
                this.getMessageId(),
                this.getAuthor().getUserId(),
                this.getTime(),
                this.getContent(),
                this.isDeleted(),
                this.getChatGroup().getGroupId()
        );
    }
}
