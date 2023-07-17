package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceInter;
import com.example.demo.service.UserServiceVerbose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Autowired
    UserRepository repo;

    @Bean
    @Qualifier("UserVerbose")
    public UserServiceInter userServiceVerbose(){
        return new UserServiceVerbose(repo);
    }

    @Bean
    @Qualifier("User")
    public UserServiceInter userService(){
        return new UserService(repo);
    }
}
