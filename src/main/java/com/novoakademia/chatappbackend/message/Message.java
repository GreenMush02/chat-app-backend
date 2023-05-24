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
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("authorId")
//    private User author;
    private String userId;
    private LocalDateTime time;
    private String content;
    private boolean isDeleted;
    private String chatGroupId;
//    @ManyToOne
//    @JoinColumn(name = "chat_group_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonProperty("chatGroupId")
//    private ChatGroup chatGroup;





    public Message(MessageDto messageDto) {
        this.userId = messageDto.getUserId();
        this.time = messageDto.getTime();
        this.content = messageDto.getContent();
        this.isDeleted = messageDto.isDeleted();
        this.chatGroupId = messageDto.getChatGroupId();
    }

    public MessageDto dto() {
        return new MessageDto(
                this.getMessageId(),
                this.getUserId(),
                this.getTime(),
                this.getContent(),
                this.isDeleted(),
                this.getChatGroupId()
        );
    }
}
