package com.example.Marketplace.repository;

import com.example.Marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT EXISTS(SELECT u FROM User u WHERE u.username = :username)")
    boolean checkUserExists(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUserName(String username);
}