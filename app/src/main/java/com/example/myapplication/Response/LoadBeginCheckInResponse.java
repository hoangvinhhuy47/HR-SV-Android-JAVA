package com.example.myapplication.Response;

import com.example.myapplication.Model.DataCheckIn;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadBeginCheckInResponse  extends  BaseReponse{
    public DataCheckIn getData() {
        return Data;
    }

    public void setData(DataCheckIn data) {
        Data = data;
    }
    @SerializedName("Data")
    @Expose
    DataCheckIn Data;
}
