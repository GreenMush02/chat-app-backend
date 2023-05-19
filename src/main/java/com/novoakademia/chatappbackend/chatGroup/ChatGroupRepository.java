package com.novoakademia.chatappbackend.chatGroup;

import com.novoakademia.chatappbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, String> {

    Optional<ChatGroup> findById(String userId);

    ChatGroup save(ChatGroup entity);
}