package com.taw.polybank.dao;

import com.taw.polybank.entity.ChatEntity;
import com.taw.polybank.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    @Query("select m from MessageEntity m where m.chatId = :chatId")
    public List<MessageEntity> searchByChat (@Param("chatId") Integer chatId);
}
