package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceInter;
import com.example.demo.service.UserServiceVerbose;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userVerbose")
public class UsercontrollerVerbose {

    @Autowired
    @Qualifier("UserVerbose")
    UserServiceInter service;

    @PostMapping("/create")
    public User saveUser(@RequestBody User user) {
       return service.createUser(user);
   }

    @GetMapping("/getusers")
     public List<String> getUser() {
        return service.getUsers();

     }

   @GetMapping("/getuser/{userid}")
   public String getUserByID(@PathVariable int userid) {

      return service.getUserById(userid);

   }

    @PutMapping("/{userid}")
    public String updateUsers(@PathVariable int userid, @RequestParam("name") Optional<String> name, @RequestParam("ttl")Optional<Integer>TTL, @RequestParam("timestamp")Optional<Timestamp>timestamp) {
        return service.updateUser(userid, name, TTL, timestamp);
    }

    @DeleteMapping("/delete/{userid}")
    public String deleteUser(@PathVariable int userid) {
        return service.deleteUser(userid);
    }
}
