package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceVerbose implements UserServiceInter{
    UserRepository repo;
    public UserServiceVerbose(UserRepository repo){
        this.repo = repo;
    }
    public User createUser(User user){
        return repo.save(user);
    }
    public List<String> getUsers(){

        List<User> user1 = new ArrayList<User>();
        repo.findAll().forEach(user -> user1.add(user));
        List<String> AllVerbose = new ArrayList<String>();
        for (User user: user1){
            String Verbose = " ";
            Field fields[] = user.getClass().getDeclaredFields();
            for (Field x : fields) {
                x.setAccessible(true);
                try {
                    Verbose += x.getName() + (x.get(user)) + "::";
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            AllVerbose.add(Verbose);
        }
        return AllVerbose;

    }
    public String getUserById(int userid){
        String Verbose = "";
        User user = repo.findById(userid).get();
        Field fields[] = user.getClass().getDeclaredFields();
        for (Field x : fields) {
            x.setAccessible(true);
            try {
                Verbose += x.getName() + (x.get(user)) + "::";
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return Verbose;

    }
    public String updateUser(int userid,Optional<String>name, Optional<Integer>TTL, Optional<Timestamp>timestamp){

        if (repo.existsById(userid)){
             User user = repo.findById(userid).get();
        if(name.isPresent()){
            user.setName(name.get());
        }
        if(TTL.isPresent()){
            user.setTTL(TTL.get());
        }
        if(timestamp.isPresent()){
            user.setTimestamp(timestamp.get());
        }
        repo.save(user);
        return "User values are updated";
        }
        return "User not found";
    }
    public String deleteUser(int userid){

        if (repo.existsById(userid)) {
            repo.deleteById(userid);
            return "User Deleted";
        } else  {
            return "User not found with ID: " + userid;
        }
    }
}
