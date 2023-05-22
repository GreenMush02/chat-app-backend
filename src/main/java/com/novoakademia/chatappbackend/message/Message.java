package com.novoakademia.chatappbackend.message;

import com.novoakademia.chatappbackend.User.User;
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
    private User author;
    private LocalDateTime time;
    private String content;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    private ChatGroup chatGroup;
}
