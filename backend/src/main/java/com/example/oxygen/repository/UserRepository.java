package com.example.oxygen.repository;

import com.example.oxygen.entity.Role;
import com.example.oxygen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<List<User>> findAllByRole(Role role);

    @Query(value = "SELECT a.id FROM users as a WHERE a.email = :email", nativeQuery = true)
    Optional<Long> findUserIdByEmail(@Param("email") String email);

    @Query(value = "SELECT a.role FROM users as a WHERE a.email = :email", nativeQuery = true)
    Optional<Role> findUserRoleByEmail(@Param("email") String email);
}
