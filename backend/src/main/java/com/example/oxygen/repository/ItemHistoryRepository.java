package com.example.oxygen.repository;

import com.example.oxygen.entity.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;


public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long> {
    @Query(value = "SELECT id, item_id, audit_timestamp, name, price\n" +
            "FROM item_history\n" +
            "WHERE audit_timestamp <= :date\n" +
            "  AND item_id = :id\n" +
            "ORDER BY audit_timestamp DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    Optional<ItemHistory> findByIdAndDate(@Param("id")  long id, @Param("date")  Date date);
}
