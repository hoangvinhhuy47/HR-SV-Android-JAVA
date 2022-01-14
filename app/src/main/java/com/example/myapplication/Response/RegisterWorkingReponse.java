package com.example.myapplication.Response;

import com.example.myapplication.Model.DataRegisterWorking;

import java.util.List;

public class RegisterWorkingReponse extends BaseReponse {
    public List<DataRegisterWorking> getData() {
        return Data;
    }

    public void setData(List<DataRegisterWorking> data) {
        Data = data;
    }

    List<DataRegisterWorking> Data;
}
