package com.example.myapplication.Service;

import com.example.myapplication.Response.ApprovalRespone;
import com.example.myapplication.Request.ApprovalRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApprovalService {
    @POST("approval")
    Call<ApprovalRespone> GetStar(@Query("GUIID") String guiid, @Body ApprovalRequest approvalRequest);
}
