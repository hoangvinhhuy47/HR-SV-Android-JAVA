package com.example.myapplication.Response;

import com.example.myapplication.Model.DataAllEquipmentAssignmentInfo;
import com.example.myapplication.Model.DataAllEquipmentRecoveryInfo;

public class GetAllEquipmentRecoveryInfoResponse extends BaseReponse{


    public DataAllEquipmentRecoveryInfo getData() {
        return Data;
    }

    public void setData(DataAllEquipmentRecoveryInfo data) {
        Data = data;
    }

    DataAllEquipmentRecoveryInfo Data;
}
