package com.example.myapplication.Service;

import com.example.myapplication.Response.SettingConfigRepose;
import com.example.myapplication.Request.SettingConfigRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SettingConfigSerVice {
    @POST("SettingConfig")
   Call<SettingConfigRepose>  SettingConfig(@Body SettingConfigRequest confirmRequest);

}
