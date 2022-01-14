package com.example.myapplication.Service;

import com.example.myapplication.Request.AddRegisterWorkingRequest;
import com.example.myapplication.Request.UpdateRegisterWorkingRequest;
import com.example.myapplication.Response.AddRegisterWorkingResponse;
import com.example.myapplication.Response.UpdateRegisterWorkingRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateRegisterApplicationService {

    @POST("RegisterLeaveApplication")
    Call<UpdateRegisterWorkingRespone> GetUpdateRegister(@Query("GUIID") String guiid, @Body UpdateRegisterWorkingRequest updateRegisterWorkingRequest);
}
