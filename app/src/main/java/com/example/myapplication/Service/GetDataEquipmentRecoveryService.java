package com.example.myapplication.Service;

import com.example.myapplication.Request.GetDataEquipmentAssimentRequest;
import com.example.myapplication.Request.GetDataEquipmentRecoveryRequest;
import com.example.myapplication.Response.GetDataEquipmentAssigmentResponse;
import com.example.myapplication.Response.GetDataEquipmentRecoveryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataEquipmentRecoveryService {
    @POST("GetEquipmentRecovery")
    Call<GetDataEquipmentRecoveryResponse> GetDataEquipmentRecoveryEmployee(@Query("GUIID") String guiid, @Body GetDataEquipmentRecoveryRequest getDataEquipmentRecoveryRequest);
}
