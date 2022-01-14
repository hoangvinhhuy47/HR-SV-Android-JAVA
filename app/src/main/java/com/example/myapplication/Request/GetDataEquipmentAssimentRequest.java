package com.example.myapplication.Request;

public class GetDataEquipmentAssimentRequest {
    public String FromDate;

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

    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String pageNumber) {
        PageNumber = pageNumber;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String ToDate;
    public String PageNumber;
    public String Status;
    public String UserID;
}
