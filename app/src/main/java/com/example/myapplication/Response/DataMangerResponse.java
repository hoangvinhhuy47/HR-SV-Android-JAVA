package com.example.myapplication.Response;

import com.example.myapplication.Model.DataManager;

import java.util.List;

public class DataMangerResponse extends BaseReponse {


    public List<DataManager> getData() {
        return Data;
    }

    public void setData(List<DataManager> data) {
        Data = data;
    }

    List<DataManager> Data;
}
