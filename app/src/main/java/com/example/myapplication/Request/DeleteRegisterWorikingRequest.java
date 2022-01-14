package com.example.myapplication.Request;

import com.example.myapplication.Model.DataDeleteRegisterWorking;

public class DeleteRegisterWorikingRequest {
    public DataDeleteRegisterWorking getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataDeleteRegisterWorking leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataDeleteRegisterWorking LeaveApplication;
}
