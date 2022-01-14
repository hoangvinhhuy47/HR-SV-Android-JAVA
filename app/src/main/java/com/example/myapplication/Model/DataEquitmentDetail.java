package com.example.myapplication.Model;

public class DataEquitmentDetail {
    private String AssignmentID;
    private String EquipmentID;

    public String getDetailID() {
        return DetailID;
    }

    public void setDetailID(String detailID) {
        DetailID = detailID;
    }

    private String DetailID;
    public String getReasonDeny() {
        return ReasonDeny;
    }

    public void setReasonDeny(String reasonDeny) {
        ReasonDeny = reasonDeny;
    }

    private String ReasonDeny;
    public String getAssignmentID() {
        return AssignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        AssignmentID = assignmentID;
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

    public String getEquipmentTypeID() {
        return EquipmentTypeID;
    }

    public void setEquipmentTypeID(String equipmentTypeID) {
        EquipmentTypeID = equipmentTypeID;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    private String EquipmentCode;
    private String EquipmentTypeID;
    private String EquipmentName;
    private String CreatedDate;
    private int Status;
}
