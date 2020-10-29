package com.daneeats.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daneeats.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    void save1(User user);
    
}
