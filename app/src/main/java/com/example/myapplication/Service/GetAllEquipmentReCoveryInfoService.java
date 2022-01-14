package com.example.myapplication.Service;

import com.example.myapplication.Request.GetAllEquipmentAssignmentInfoRequest;
import com.example.myapplication.Request.GetAllEquipmentRecoveryInfoRequest;
import com.example.myapplication.Response.GetAllEquipmentAssignmentInfoResponse;
import com.example.myapplication.Response.GetAllEquipmentRecoveryInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAllEquipmentReCoveryInfoService {
    @POST("GetEquipmentRecoveryInfo")
    Call<GetAllEquipmentRecoveryInfoResponse> GetEquipmentRecoveryInfo(@Query("GUIID") String guiid, @Body GetAllEquipmentRecoveryInfoRequest getAllEquipmentRecoveryInfoRequest);
}
