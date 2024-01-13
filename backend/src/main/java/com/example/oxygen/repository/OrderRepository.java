package com.example.oxygen.repository;

import com.example.oxygen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(long id);
    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.user.id = :userId")
    List<Order> findAllByUserId(@Param("userId") Long userId);
}
