package com.example.myapplication.Service;

import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Request.LoadBeginCheckInRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoadBeginCheckInSerVice {
    @POST("LoadBeginCheckTime")
    Call<LoadBeginCheckInResponse> GetLoadBegin(@Query("GUIID") String guiid, @Body LoadBeginCheckInRequest loadBeginCheckInRequest);
}
