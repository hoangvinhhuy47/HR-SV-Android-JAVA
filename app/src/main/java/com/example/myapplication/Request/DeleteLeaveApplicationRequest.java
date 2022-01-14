package com.example.myapplication.Request;

import com.example.myapplication.Model.DataDeleteLeaveApplication;

public class DeleteLeaveApplicationRequest {
    public DataDeleteLeaveApplication getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataDeleteLeaveApplication leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataDeleteLeaveApplication LeaveApplication;
}
