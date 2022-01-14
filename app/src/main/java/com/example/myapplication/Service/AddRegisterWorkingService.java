package com.example.myapplication.Service;

import com.example.myapplication.Request.AddRegisterWorkingRequest;
import com.example.myapplication.Response.AddRegisterWorkingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddRegisterWorkingService {
    @POST("RegisterLeaveApplication")
    Call<AddRegisterWorkingResponse> GetRegisterWorking(@Query("GUIID") String guiid, @Body AddRegisterWorkingRequest addRegisterWorkingRequest);
}
