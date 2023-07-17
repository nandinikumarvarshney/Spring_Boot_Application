package com.example.demo.service;

import com.example.demo.entity.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserServiceInter {
    public User createUser(User user);
    public List<String> getUsers();

    public String getUserById(int userid);
    public String updateUser(int userid, Optional<String> name, Optional<Integer>TTL, Optional<Timestamp>timestamp);
    public String deleteUser(int userid);
}
