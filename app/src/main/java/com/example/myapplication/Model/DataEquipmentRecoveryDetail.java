package com.example.myapplication.Model;

public class DataEquipmentRecoveryDetail {
private String RecoveryID;
private String EquipmentID;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    private int Status;
    public String getRecoveryID() {
        return RecoveryID;
    }

    public void setRecoveryID(String recoveryID) {
        RecoveryID = recoveryID;
    }

    public String getEquipmentID() {
        return EquipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        EquipmentID = equipmentID;
    }

    public String getEquipmentCode() {
        return EquipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        EquipmentCode = equipmentCode;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }

    public String getDetailID() {
        return DetailID;
    }

    public void setDetailID(String detailID) {
        DetailID = detailID;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    private String EquipmentCode;
private String EquipmentName;
private String DetailID;
private String Notes;
}
