package com.example.myapplication.Response;

import com.example.myapplication.Model.DataEquipmentEmployee;

import java.util.List;

public class GetDataEquipmentAssigmentResponse extends BaseReponse{
    public List<DataEquipmentEmployee> getData() {
        return Data;
    }

    public void setData(List<DataEquipmentEmployee> data) {
        Data = data;
    }

    List<DataEquipmentEmployee> Data;
}
