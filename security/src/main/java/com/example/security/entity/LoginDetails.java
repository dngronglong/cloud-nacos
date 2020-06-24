package com.example.security.entity;

public class LoginDetails {
    private Boolean rememberMe;
    private String verifyCode;

    public LoginDetails(Boolean rememberMe, String verifyCode) {
        this.rememberMe = rememberMe;
        this.verifyCode = verifyCode;
    }

    public LoginDetails() {}

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
