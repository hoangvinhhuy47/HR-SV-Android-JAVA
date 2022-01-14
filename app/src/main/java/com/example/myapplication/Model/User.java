package com.example.myapplication.Model;

public class User {
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPasswordEncode() {
        return PasswordEncode;
    }

    public void setPasswordEncode(String passwordEncode) {
        PasswordEncode = passwordEncode;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    private String UserID;
    private String UserName;
    private String Password;
    private String PasswordEncode;
    private String FullName;
}
