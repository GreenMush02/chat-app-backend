package com.novoakademia.chatappbackend.User;

import com.novoakademia.chatappbackend.chatGroup.ChatGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String userName;

    private String email;

    private String password;

    private boolean isAdmin;

    private boolean isBanned;

    @ManyToMany
    @JoinTable(
            name = "user_chat_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<ChatGroup> chatGroups;

    public User(String userName, String email, String password, boolean isAdmin, boolean isBanned) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
    }
}
