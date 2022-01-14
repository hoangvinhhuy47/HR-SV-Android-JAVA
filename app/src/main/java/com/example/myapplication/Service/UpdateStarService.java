package com.example.myapplication.Service;

import com.example.myapplication.Response.UpdateStarReponse;
import com.example.myapplication.Request.UpdateStarRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateStarService {
    @POST("updateStar")
    Call<UpdateStarReponse> UpdateStar(@Query("GUIID") String guiid,@Body UpdateStarRequest updateStarRequest);
}
