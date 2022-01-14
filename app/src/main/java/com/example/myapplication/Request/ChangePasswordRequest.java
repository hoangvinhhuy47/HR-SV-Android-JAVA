package com.example.myapplication.Request;

public class ChangePasswordRequest {
    public String UserID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCurrentPassword() {
        return CurrentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        CurrentPassword = currentPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }

    public String CurrentPassword;
    public String NewPassword;
}