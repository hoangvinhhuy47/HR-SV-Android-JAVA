package com.example.myapplication.Request;

import com.example.myapplication.Model.DataAddRegisterWorking;

public class AddRegisterWorkingRequest {
    public DataAddRegisterWorking getLeaveApplication() {
        return LeaveApplication;
    }

    public void setLeaveApplication(DataAddRegisterWorking leaveApplication) {
        LeaveApplication = leaveApplication;
    }

    DataAddRegisterWorking LeaveApplication;
}
