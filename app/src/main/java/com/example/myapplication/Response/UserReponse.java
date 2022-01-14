package com.example.myapplication.Response;


import com.example.myapplication.Model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReponse extends BaseReponse {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @SerializedName("User")
    @Expose
    private User user;
}
