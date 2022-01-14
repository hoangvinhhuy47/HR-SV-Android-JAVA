package com.example.myapplication.Model;

public class Task {
    //    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
    private String TaskEmployeeID;
    private String TaskID;
    private String EmployeeID;
    private boolean IsStar;
    private int TaskStatus;
    private String Notes;
    private int FinalPercent;
    private String FinalDate;
    private String ReasonDeny;
    private String TaskName;
    private String FromDate;
    private String ToDate;
    private int Priority;
    private String CreatedDate;
    private String  Status;
    private int SiteID;
    private String StoreID;

//    public String getStatusName() {
//        return StatusName;
//    }
//
//    public void setStatusName(String statusName) {
//            if (getStatus() == 1) {
//                StatusName = "Đang Hoạt Động";
//            } else if (getStatus() == 2) {
//            StatusName = "Đã Hủy";
//        }
//
//    }
//
//    private String StatusName;



    public boolean isStar() {
        return IsStar;
    }

    public void setStar(boolean star) {
        IsStar = star;
    }



    public String getTaskEmployeeID() {
        return TaskEmployeeID;
    }

    public void setTaskEmployeeID(String taskEmployeeID) {
        TaskEmployeeID = taskEmployeeID;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public boolean getIsStar() {
        return IsStar;
    }

    public void setIsStar(boolean isStar) {
        IsStar = isStar;
    }

    public int getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        TaskStatus = taskStatus;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getFinalPercent() {
        return FinalPercent;
    }

    public void setFinalPercent(int finalPercent) {
        FinalPercent = finalPercent;
    }

    public String getFinalDate() {
        return FinalDate;
    }

    public void setFinalDate(String finalDate) {
        FinalDate = finalDate;
    }

    public String getReasonDeny() {
        return ReasonDeny;
    }

    public void setReasonDeny(String reasonDeny) {
        ReasonDeny = reasonDeny;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String  getStatus() {
        return Status;
    }

    public void setStatus(String  status) {
        Status = status;
    }

    public int getSiteID() {
        return SiteID;
    }

    public void setSiteID(int siteID) {
        SiteID = siteID;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }


}

