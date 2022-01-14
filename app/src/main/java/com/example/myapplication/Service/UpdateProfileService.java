package com.example.myapplication.Service;

import com.example.myapplication.Request.UpdateProfileRequest;
import com.example.myapplication.Request.UpdateRegisterWorkingRequest;
import com.example.myapplication.Response.UpdateProfileResponse;
import com.example.myapplication.Response.UpdateRegisterWorkingRespone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateProfileService {
    @POST("UpdateProfile")
    Call<UpdateProfileResponse> UpdateProfile(@Query("GUIID") String guiid, @Body UpdateProfileRequest updateProfileRequest);

}
