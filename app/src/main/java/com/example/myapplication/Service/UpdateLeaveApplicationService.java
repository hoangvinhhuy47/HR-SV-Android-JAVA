package com.example.myapplication.Service;

import com.example.myapplication.Request.AddLeaveApplicationRequest;
import com.example.myapplication.Request.UpdateLeaveApplicationRequest;
import com.example.myapplication.Response.AddLeaveApplicationResponse;
import com.example.myapplication.Response.UpdateLeaveApplicationReponse;
import com.example.myapplication.Response.UpdatePercentRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateLeaveApplicationService {
    @POST("RegisterLeaveApplication")
    Call<UpdateLeaveApplicationReponse> GetDataUpdateLeave(@Query("GUIID") String guiid, @Body UpdateLeaveApplicationRequest updateLeaveApplicationRequest);
}
