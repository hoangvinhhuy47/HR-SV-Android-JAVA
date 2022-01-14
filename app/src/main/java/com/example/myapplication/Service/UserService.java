package com.example.myapplication.Service;

import com.example.myapplication.Response.UserReponse;
import com.example.myapplication.Request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
//    LoginRequest abc = new LoginRequest("trungnguyen","123456");
    @POST("CheckLogin")
    Call<UserReponse> CheckLogin(@Query("GUIID") String guiid, @Body LoginRequest loginRequest);
}

