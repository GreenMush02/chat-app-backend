package com.novoakademia.chatappbackend.chatgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, String> {


    Optional<ChatGroup> findById(String chatGroupId);

    ChatGroup save(ChatGroup entity);


}