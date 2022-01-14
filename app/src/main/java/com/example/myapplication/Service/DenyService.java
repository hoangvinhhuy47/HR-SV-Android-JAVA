package com.example.myapplication.Service;

import com.example.myapplication.Response.DenyReponse;
import com.example.myapplication.Request.DenyRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DenyService {
    @POST("deny")
    Call<DenyReponse> Deny(@Query("GUIID") String guiid, @Body DenyRequest denyRequest);
}
