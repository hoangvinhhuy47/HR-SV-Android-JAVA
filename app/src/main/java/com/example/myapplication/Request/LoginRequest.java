package com.example.myapplication.Request;

public class LoginRequest {
    public LoginRequest() {

    }

    public String getUserName(String s) {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword(String s) {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    private String UserName;
    public LoginRequest(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    private String Password;


}
