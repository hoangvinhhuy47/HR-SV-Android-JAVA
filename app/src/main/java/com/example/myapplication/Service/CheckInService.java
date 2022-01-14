package com.example.myapplication.Service;

import com.example.myapplication.Response.CheckInReponse;
import com.example.myapplication.Request.CheckinRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CheckInService {
      @POST("CheckTime")
    Call<CheckInReponse> GetCheckIn(@Query("GUIID") String guiid, @Body CheckinRequest checkinRequest);
}
