package com.taw.polybank.dao;

import com.taw.polybank.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    @Query("select c from ChatEntity c where c.assistantId = :employeeId")
    public List<ChatEntity> searchByAssistant (@Param("employeeId") Integer employeeId);
}
