package com.example.security.repository.impl;

import com.example.security.repository.VerifyCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryVerifyCodeRepositoryImpl implements VerifyCodeRepository {

    private static final int DEFAULT_VERIFY_CODE_LENGTH = 4;

    private static final Map<String, String> REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public void save(String key, String code) {
        REPOSITORY.put(key, code);
    }

    @Override
    public String find(String key) {
        return REPOSITORY.get(key);
    }

    @Override
    public void remove(String key) {
        REPOSITORY.remove(key);
    }

}
