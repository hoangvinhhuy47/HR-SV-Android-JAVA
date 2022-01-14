package com.example.myapplication.Response;

import com.example.myapplication.Model.DataEquipmentEmployee;
import com.example.myapplication.Model.DataEquipmentRecovery;

import java.util.List;

public class GetDataEquipmentRecoveryResponse extends BaseReponse{
    public List<DataEquipmentRecovery> getData() {
        return Data;
    }

    public void setData(List<DataEquipmentRecovery> data) {
        Data = data;
    }

    List<DataEquipmentRecovery> Data;
}
