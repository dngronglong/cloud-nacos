package com.example.common.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private Boolean rememberMe;
    private String verifyCode;
    private String power;
    private Long expirationTime;
    private List<?> authorities;
}
