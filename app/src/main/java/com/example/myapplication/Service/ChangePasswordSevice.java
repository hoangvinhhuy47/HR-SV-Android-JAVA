package com.example.myapplication.Service;

import com.example.myapplication.Request.ChangePasswordRequest;
import com.example.myapplication.Request.GetProfileRequest;
import com.example.myapplication.Response.ChangePasswordResponse;
import com.example.myapplication.Response.GetProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ChangePasswordSevice {
    @POST("ChangePassword")
    Call<ChangePasswordResponse> ChangePassword(@Query("GUIID") String guiid, @Body ChangePasswordRequest changePasswordRequest);
}