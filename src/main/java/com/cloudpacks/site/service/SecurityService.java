package com.cloudpacks.site.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
