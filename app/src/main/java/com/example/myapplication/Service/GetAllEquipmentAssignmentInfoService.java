package com.example.myapplication.Service;

import com.example.myapplication.Request.DeleteRegisterWorikingRequest;
import com.example.myapplication.Request.GetAllEquipmentAssignmentInfoRequest;
import com.example.myapplication.Response.DeleteRegisterWorikingResponse;
import com.example.myapplication.Response.GetAllEquipmentAssignmentInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetAllEquipmentAssignmentInfoService {
    @POST("GetAllEquipmentAssignmentInfo")
    Call<GetAllEquipmentAssignmentInfoResponse> GetAllEquipmentAssignmentInfo(@Query("GUIID") String guiid, @Body GetAllEquipmentAssignmentInfoRequest getAllEquipmentAssignmentInfoRequest);
}
