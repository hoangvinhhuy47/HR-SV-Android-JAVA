package com.example.myapplication.Service;

import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Request.WorkingAttendaceRequest;
import com.example.myapplication.Response.DataWorkingAttendaceRespone;
import com.example.myapplication.Response.LeaveApplicationResopnse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LeaveApplicationSerVice {
    @POST("GetDataLeaveApplication")
    Call<LeaveApplicationResopnse> GetDataLeaveApplication(@Query("GUIID") String guiid, @Body LeaveApplicationRequest leaveApplicationRequest);
}
