package com.example.myapplication.Model;

public class DataEquipmentEmployee {
    private String EmployeeID;
    private String CreatedDate;
    private String CreatedBy;
    private int Status;
    private String Notes;

    public int getStatus() {
        return Status;
    }

    public String getAssignmentID() {
        return AssignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        AssignmentID = assignmentID;
    }

    private String AssignmentID;
    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public int isStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
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

    private String EmployeeCode;
    private String FullName;
    private String AssignmentEmployeeName;
    private String StatusName;
    private String SiteID;

    public String getSiteID() {
        return SiteID;
    }

    public void setSiteID(String siteID) {
        SiteID = siteID;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public boolean isPost() {
        return IsPost;
    }

    public void setPost(boolean post) {
        IsPost = post;
    }

    public String getPostedDate() {
        return PostedDate;
    }

    public void setPostedDate(String postedDate) {
        PostedDate = postedDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    private String StoreID;
    private boolean IsPost;
    private String PostedDate;
    private String ToDate;
}
