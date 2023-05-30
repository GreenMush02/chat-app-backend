package com.novoakademia.chatappbackend.chatgroup;

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
public class ChatGroupDto {

    private String groupId;
    private String groupName;
    private List<String> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private boolean isPriv;
    private String queueId;

}