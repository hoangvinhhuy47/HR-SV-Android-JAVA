package com.example.myapplication.Model;

public class DataUpdateLeaveApplication {
    public String LeaveID;
    public String SiteID;
    public String StoreID;
    public String UserID;

    public int getLeaveApplicationType() {
        return LeaveApplicationType;
    }

    public void setLeaveApplicationType(int leaveApplicationType) {
        LeaveApplicationType = leaveApplicationType;
    }

    public int LeaveApplicationType;
    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    public String ManagerID;


    public String getLeaveID() {
        return LeaveID;
    }

    public void setLeaveID(String leaveID) {
        LeaveID = leaveID;
    }



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

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
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

    public String getSendDate() {
        return SendDate;
    }

    public void setSendDate(String sendDate) {
        SendDate = sendDate;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getLeaveReason() {
        return LeaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        LeaveReason = leaveReason;
    }

    public String CreatedDate;
    public String FromDate;
    public String ToDate;
    public String SendDate;
    public int Status;
    public String LeaveReason;

    public String getSymbolID() {
        return SymbolID;
    }

    public void setSymbolID(String symbolID) {
        SymbolID = symbolID;
    }

    public String SymbolID;
}
