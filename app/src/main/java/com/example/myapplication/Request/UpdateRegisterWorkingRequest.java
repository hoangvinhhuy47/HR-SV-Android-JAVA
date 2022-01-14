package com.example.myapplication.Request;

import com.example.myapplication.Model.DataUpdateRegiseterWorking;

public class UpdateRegisterWorkingRequest {


    public DataUpdateRegiseterWorking getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataUpdateRegiseterWorking leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataUpdateRegiseterWorking LeaveApplication	;
}
