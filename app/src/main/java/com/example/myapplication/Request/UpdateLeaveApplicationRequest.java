package com.example.myapplication.Request;

import com.example.myapplication.Model.DataUpdateLeaveApplication;

public class UpdateLeaveApplicationRequest {
    public DataUpdateLeaveApplication getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataUpdateLeaveApplication leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataUpdateLeaveApplication LeaveApplication;
}
