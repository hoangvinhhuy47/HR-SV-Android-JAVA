package com.example.myapplication.Request;

import com.example.myapplication.Model.DataGetProfile;

public class UpdateProfileRequest {
    public DataGetProfile getUserProfile() {
        return UserProfile;
    }

    public void setUserProfile(DataGetProfile userProfile) {
        UserProfile = userProfile;
    }

    DataGetProfile UserProfile;
}
