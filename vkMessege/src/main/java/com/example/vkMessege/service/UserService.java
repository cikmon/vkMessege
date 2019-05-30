package com.example.vkMessege.service;

import com.example.vkMessege.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllLocal();
    void save(List<User> users);
    void save(User user);
    String getAllLocalJson();
    void delete(User user);
    String findById(String id);
}


