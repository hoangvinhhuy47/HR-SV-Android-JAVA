package com.example.myapplication.Service;

import com.example.myapplication.Request.WorkingAttendaceRequest;
import com.example.myapplication.Request.WorkingEmployeeRequest;
import com.example.myapplication.Response.DataWorkingAttendaceRespone;
import com.example.myapplication.Response.WorkingEmployeeRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WorkingEmployeeService {
    @POST("GetDataEmployeeWorking")
    Call<WorkingEmployeeRespone> GetDataWorkingEmployee(@Query("GUIID") String guiid, @Body WorkingEmployeeRequest workingEmployeeRequest);
}

