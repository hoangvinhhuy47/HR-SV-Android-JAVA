package com.example.myapplication.Service;

import com.example.myapplication.Request.AddRegisterWorkingRequest;
import com.example.myapplication.Request.DeleteLeaveApplicationRequest;
import com.example.myapplication.Response.AddRegisterWorkingResponse;
import com.example.myapplication.Response.DeleteLeaveApplicationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeleteLeaveApplicationService {
    @POST("DeleteLeaveApplication")
    Call<DeleteLeaveApplicationResponse> GetDeleLeave(@Query("GUIID") String guiid, @Body DeleteLeaveApplicationRequest deleteLeaveApplicationRequest);
}
