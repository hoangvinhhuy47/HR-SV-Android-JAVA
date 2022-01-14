package com.example.myapplication.Service;

import com.example.myapplication.Request.CheckinRequest;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Response.CheckInReponse;
import com.example.myapplication.Response.DataMangerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataMangerSevice {
    @POST("GetManagerByEmployeeId")
    Call<DataMangerResponse> GetManager(@Query("GUIID") String guiid, @Body DataMangerRequest dataMangerRequest);
}
