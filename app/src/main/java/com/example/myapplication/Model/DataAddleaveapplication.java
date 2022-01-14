package com.example.myapplication.Model;

public class DataAddleaveapplication {
    public String LeaveID;
    public String UserId;

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

    public String getSymbolID() {
        return SymbolID;
    }

    public void setSymbolID(String symbolID) {
        SymbolID = symbolID;
    }

    public String SymbolID;

    public String getLeaveID() {
        return LeaveID;
    }

    public void setLeaveID(String leaveID) {
        LeaveID = leaveID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public int Status;
    public String LeaveReason;
    public String FromDate;
    public String ToDate;

}
