package com.example.security.config;

import com.example.security.utils.VerifyCodeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebVerifyCodeConfig {
    @Bean
    public VerifyCodeUtil verifyCodeUtil() {
        return new VerifyCodeUtil();
    }
}
