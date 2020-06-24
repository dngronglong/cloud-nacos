package com.example.security.repository;

public interface VerifyCodeRepository {
    void save(String key, String code);

    String find(String key);

    void remove(String key);
}
