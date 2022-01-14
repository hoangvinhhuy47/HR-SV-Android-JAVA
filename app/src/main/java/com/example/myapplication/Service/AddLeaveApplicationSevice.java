package com.example.myapplication.Service;

import com.example.myapplication.Request.AddLeaveApplicationRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Response.AddLeaveApplicationResponse;
import com.example.myapplication.Response.LeaveApplicationResopnse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddLeaveApplicationSevice {
    @POST("RegisterLeaveApplication")
    Call<AddLeaveApplicationResponse> GetDataLeaveApplication(@Query("GUIID") String guiid, @Body AddLeaveApplicationRequest addLeaveApplicationRequest);
}
