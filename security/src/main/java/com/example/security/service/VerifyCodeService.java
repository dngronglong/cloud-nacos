package com.example.security.service;

import java.awt.*;

public interface VerifyCodeService {
    void send(String key);

    void verify(String key, String code);

    Image image(String key);
}
