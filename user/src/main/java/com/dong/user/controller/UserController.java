package com.dong.user.controller;

import com.dong.user.api.UserServer;
import com.example.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @GetMapping("/{id}")
    public String findUser(@PathVariable("id") String id){
        return userServer.findById(id);
    }
}
