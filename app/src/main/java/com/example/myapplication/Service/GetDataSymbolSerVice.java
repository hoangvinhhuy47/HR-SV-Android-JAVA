package com.example.myapplication.Service;

import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.LeaveApplicationResopnse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataSymbolSerVice {
    @POST("GetDataSymbol")
    Call<GetDataSymbolResponse> GetDataSymbol(@Query("GUIID") String guiid, @Body GetDataSymbolRequest getDataSymbolRequest);
}
