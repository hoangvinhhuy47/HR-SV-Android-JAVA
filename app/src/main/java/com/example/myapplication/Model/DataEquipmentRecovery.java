package com.example.myapplication.Model;

public class DataEquipmentRecovery {
   private String EmployeeID;
   private String RecoveryDate;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    private int Status;
    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getRecoveryDate() {
        return RecoveryDate;
    }

    public void setRecoveryDate(String recoveryDate) {
        RecoveryDate = recoveryDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getRecoveryID() {
        return RecoveryID;
    }

    public void setRecoveryID(String recoveryID) {
        RecoveryID = recoveryID;
    }

    public String getAssignmentEmployeeName() {
        return AssignmentEmployeeName;
    }

    public void setAssignmentEmployeeName(String assignmentEmployeeName) {
        AssignmentEmployeeName = assignmentEmployeeName;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    private String CreatedBy;
   private String CreatedDate;
   private String Notes;
   private String EmployeeCode;
   private String FullName;
   private String RecoveryID;
   private String AssignmentEmployeeName;
   private String StatusName;
}
