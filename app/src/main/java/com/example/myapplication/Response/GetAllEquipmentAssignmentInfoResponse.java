package com.example.myapplication.Response;

import com.example.myapplication.Model.DataAllEquipmentAssignmentInfo;
import com.example.myapplication.Model.DataEquitmentDetail;

import java.util.List;

public class GetAllEquipmentAssignmentInfoResponse  extends BaseReponse{
    public DataAllEquipmentAssignmentInfo getData() {
        return Data;
    }

    public void setData(DataAllEquipmentAssignmentInfo data) {
        Data = data;
    }

    DataAllEquipmentAssignmentInfo Data;
}
