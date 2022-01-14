package com.example.myapplication.Service;

import com.example.myapplication.Request.AddRegisterWorkingRequest;
import com.example.myapplication.Request.RegisterWorkingRequest;
import com.example.myapplication.Response.AddRegisterWorkingResponse;
import com.example.myapplication.Response.RegisterWorkingReponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RegisterWorkingSevice {
    @POST("GetDataOTApplication")
    Call<RegisterWorkingReponse> GetDataRegister(@Query("GUIID") String guiid, @Body RegisterWorkingRequest registerWorkingRequest);
}
