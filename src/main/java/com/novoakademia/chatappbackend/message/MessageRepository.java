package com.novoakademia.chatappbackend.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Override
    List<Message> findAll();

    @Override
    Optional<Message> findById(String s);

    @Override
    Message save(Message entity);

    @Query(value = "SELECT * FROM messages m WHERE m.chat_group_id = :id", nativeQuery = true)
    List<Message> findMessagesByGroupId(@Param("id") String id);
}
