package com.example.myapplication.Service;

import com.example.myapplication.Request.GetProfileRequest;
import com.example.myapplication.Response.GetProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetProfileService {
    @POST("GetProfile")
    Call<GetProfileResponse> Data(@Query("GUIID") String guiid, @Body GetProfileRequest getProfileRequest);

}
