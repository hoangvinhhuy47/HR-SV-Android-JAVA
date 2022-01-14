package com.example.myapplication.Response;

import com.example.myapplication.Model.DataWorkingAttendace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWorkingAttendaceRespone extends BaseReponse {
    public List<DataWorkingAttendace> getData() {
        return Data;
    }

    public void setData(List<DataWorkingAttendace> data) {
        Data = data;
    }
    @SerializedName("Data")
    @Expose
  private List<DataWorkingAttendace> Data;






}
