package com.qrcraze.qrcraze.Data;

public class LoginResponse {
    private String loginStatus;

    public LoginResponse(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
