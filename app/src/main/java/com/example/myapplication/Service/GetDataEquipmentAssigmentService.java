package com.example.myapplication.Service;

import com.example.myapplication.Request.GetDataEquipmentAssimentRequest;
import com.example.myapplication.Response.GetDataEquipmentAssigmentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataEquipmentAssigmentService {
    @POST("GetDataEquipmentEmployee")
    Call<GetDataEquipmentAssigmentResponse> GetDataEquipmentEmployee(@Query("GUIID") String guiid, @Body GetDataEquipmentAssimentRequest getDataEquipmentAssimentRequest);
}
