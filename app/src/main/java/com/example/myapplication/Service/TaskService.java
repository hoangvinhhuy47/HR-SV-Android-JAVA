package com.example.myapplication.Service;

import com.example.myapplication.Response.TaskReponse;
import com.example.myapplication.Request.TaskRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TaskService {
    @POST("GetDataTaskEmployee")
    Call<TaskReponse> GetData(@Query("GUIID") String guiid, @Body TaskRequest taskRequest);
}
