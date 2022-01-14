package com.example.myapplication.Request;

import com.example.myapplication.Model.DataAllEquipmentAssignmentInfo;
import com.example.myapplication.Model.DataEquipmentEmployee;
import com.example.myapplication.Model.DataEquipmentRecovery;
import com.example.myapplication.Model.DataEquitmentDetail;

import java.util.List;

public class AcceptEquipmentRecoveryRequest {
    public String getRecoveryID() {
        return RecoveryID;
    }

    public void setRecoveryID(String recoveryID) {
        RecoveryID = recoveryID;
    }

    private String RecoveryID;
}
