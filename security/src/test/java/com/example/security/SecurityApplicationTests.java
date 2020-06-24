package com.example.security;

import com.example.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        System.out.println(userRepository.findUserByUsername("admin"));
//        userMapper.selectById(1)
    }

}
