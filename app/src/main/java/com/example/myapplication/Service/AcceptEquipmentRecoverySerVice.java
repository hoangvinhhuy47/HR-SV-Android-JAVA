package com.example.myapplication.Service;

import com.example.myapplication.Request.AcceptEquipmentRecoveryRequest;
import com.example.myapplication.Request.AddLeaveApplicationRequest;
import com.example.myapplication.Response.AcceptEquipmentRecoveryResponse;
import com.example.myapplication.Response.AddLeaveApplicationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AcceptEquipmentRecoverySerVice {
    @POST("AcceptEquipmentRecovery")
    Call<AcceptEquipmentRecoveryResponse> AcceptEquipmentRecovery(@Query("GUIID") String guiid, @Body AcceptEquipmentRecoveryRequest acceptEquipmentRecoveryRequest);

}
