package com.taw.polybank.dao;

import com.taw.polybank.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    @Query("SELECT c FROM ClientEntity c WHERE NOT EXISTS (SELECT t FROM c.transactionsById t WHERE t.timestamp < :timestamp)")
    List<ClientEntity> findAllInactiveFrom(@Param("lowerBoundTime")Timestamp timestamp);
}
