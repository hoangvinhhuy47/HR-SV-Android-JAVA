package com.example.myapplication.Model;

public class DataAssigment {
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDetailID() {
        return DetailID;
    }

    public void setDetailID(String detailID) {
        DetailID = detailID;
    }

    public String getReasonDeny() {
        return ReasonDeny;
    }

    public void setReasonDeny(String reasonDeny) {
        ReasonDeny = reasonDeny;
    }

    private String Status;
    private String DetailID;
    private String ReasonDeny;
}
