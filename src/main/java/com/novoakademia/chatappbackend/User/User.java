package com.novoakademia.chatappbackend.User;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novoakademia.chatappbackend.chatgroup.ChatGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String userName;

    private String email;

    private String password;

    private String avatarColor;

    private boolean isAdmin;

    private boolean isBanned;

    @ManyToMany
    @JoinTable(
            name = "user_chat_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<ChatGroup> chatGroups = new ArrayList<>();

    public User(String userName, String email, String password, boolean isAdmin, boolean isBanned) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }

    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.avatarColor = userDto.getAvatarColor();
        this.isAdmin = userDto.isAdmin();
        this.isBanned = userDto.isBanned();
    }
    public UserDto dto() {
        return new UserDto(
                this.getUserId(),
                this.getUserName(),
                this.getEmail(),
                this.getPassword(),
                this.getAvatarColor(),
                this.isAdmin(),
                this.isBanned()
        );
    }


}
