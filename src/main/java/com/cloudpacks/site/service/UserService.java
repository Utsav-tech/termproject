package com.cloudpacks.site.service;

import com.cloudpacks.site.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    void save1(User user);
}
