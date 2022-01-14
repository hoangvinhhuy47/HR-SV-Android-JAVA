package com.example.myapplication.Service;

import com.example.myapplication.Response.UpdatePercentRespone;
import com.example.myapplication.Request.UpdatePercentRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdatePercentService {
    @POST("updatePercent")
    Call<UpdatePercentRespone> updatepercent(@Query("GUIID") String guiid, @Body UpdatePercentRequest updatePercentRequest);
}