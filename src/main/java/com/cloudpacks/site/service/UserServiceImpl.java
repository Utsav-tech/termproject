package com.cloudpacks.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudpacks.site.model.User;
import com.cloudpacks.site.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
    }
    public void save1(User user) {
        
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
