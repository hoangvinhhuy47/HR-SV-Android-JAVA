package com.example.myapplication.Model;

import java.util.List;

public class DataAllEquipmentRecoveryInfo {


    DataEquipmentRecovery Recovery;

    public DataEquipmentRecovery getRecovery() {
        return Recovery;
    }

    public void setRecovery(DataEquipmentRecovery recovery) {
        Recovery = recovery;
    }

    public List<DataEquipmentRecoveryDetail> getRecoveryDetails() {
        return RecoveryDetails;
    }

    public void setRecoveryDetails(List<DataEquipmentRecoveryDetail> recoveryDetails) {
        RecoveryDetails = recoveryDetails;
    }

    List<DataEquipmentRecoveryDetail> RecoveryDetails;
}
