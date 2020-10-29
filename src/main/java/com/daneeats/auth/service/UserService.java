package com.daneeats.auth.service;

import com.daneeats.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    void save1(User user);
}
