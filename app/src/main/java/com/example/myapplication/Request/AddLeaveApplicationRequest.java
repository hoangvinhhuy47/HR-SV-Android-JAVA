package com.example.myapplication.Request;

import com.example.myapplication.Model.DataAddleaveapplication;

public class AddLeaveApplicationRequest {
    public DataAddleaveapplication getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataAddleaveapplication leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataAddleaveapplication LeaveApplication;
}
