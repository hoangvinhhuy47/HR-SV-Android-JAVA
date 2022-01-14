package com.example.myapplication.Service;

import com.example.myapplication.Request.AcceptEquipmentRecoveryRequest;
import com.example.myapplication.Request.AcceptEquitmentAssimentRequest;
import com.example.myapplication.Response.AcceptEquipmentRecoveryResponse;
import com.example.myapplication.Response.AcceptEquitmentAssimentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AcceptEquitmentAssimentService {
    @POST("AcceptEquipmentAssignment")
    Call<AcceptEquitmentAssimentResponse> AcceptEquitmentAssimentResponse(@Query("GUIID") String guiid, @Body AcceptEquitmentAssimentRequest acceptEquitmentAssimentRequest);

}
