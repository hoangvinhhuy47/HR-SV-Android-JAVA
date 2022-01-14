package com.example.myapplication.Service;

import com.example.myapplication.Request.DeleteLeaveApplicationRequest;
import com.example.myapplication.Request.DeleteRegisterWorikingRequest;
import com.example.myapplication.Response.DeleteLeaveApplicationResponse;
import com.example.myapplication.Response.DeleteRegisterWorikingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeleteRegisterWorikingService {
    @POST("DeleteLeaveApplication")
    Call<DeleteRegisterWorikingResponse> GetDeleRegister(@Query("GUIID") String guiid, @Body DeleteRegisterWorikingRequest deleteRegisterWorikingRequest);
}
