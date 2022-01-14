package com.example.myapplication.Response;

import com.example.myapplication.Model.DataWorkingEmployee;

import java.util.List;

public class WorkingEmployeeRespone extends BaseReponse {
    public List<DataWorkingEmployee> getData() {
        return Data;
    }

    public void setData(List<DataWorkingEmployee> data) {
        Data = data;
    }

    List<DataWorkingEmployee> Data;
}
