package com.example.myapplication.Service;

import com.example.myapplication.Response.DataWorkingAttendaceRespone;
import com.example.myapplication.Request.WorkingAttendaceRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WorkingAttendaceSerVice {
    @POST("GetDataWorkingAttendance")
    Call<DataWorkingAttendaceRespone> GetDataWorking(@Query("GUIID") String guiid, @Body WorkingAttendaceRequest workingAttendaceRequest);
}
