package com.example.myapplication.Request;

public class GetAllEquipmentRecoveryInfoRequest {

    public String getRecoveryID() {
        return RecoveryID;
    }

    public void setRecoveryID(String recoveryID) {
        RecoveryID = recoveryID;
    }

    private String RecoveryID;
}
