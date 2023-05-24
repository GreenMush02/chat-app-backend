package com.novoakademia.chatappbackend.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String messageId;
    private String userId;
    private LocalDateTime time;
    private String content;
    private boolean isDeleted;
    private String chatGroupId;


}