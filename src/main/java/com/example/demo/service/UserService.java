package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class UserService implements UserServiceInter{

     UserRepository repo;
     public UserService(UserRepository repo){
         this.repo = repo;
     }

    public User createUser(User user){
        return repo.save(user);
    }
    public List<String> getUsers(){
        List<User> user1 = new ArrayList<User>();
        repo.findAll().forEach(user -> user1.add(user));
        List<String> userString = new ArrayList<String>();
        userString.add(user1.toString());
        return userString; //List of JSON strings is returned
    }
    public String getUserById(int userid){

       User user = repo.findById(userid).orElse(null);
       return user.toString(); //Converted into JSON String



    }
    public String updateUser(int userid, Optional<String> name, Optional<Integer>TTL, Optional<Timestamp>timestamp){

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

