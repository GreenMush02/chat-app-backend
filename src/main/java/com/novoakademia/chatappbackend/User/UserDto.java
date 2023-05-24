package com.novoakademia.chatappbackend.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String avatarColor;
    private boolean isAdmin;
    private boolean isBanned;
}
