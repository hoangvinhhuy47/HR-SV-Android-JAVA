package com.example.myapplication.Request;

import com.example.myapplication.Model.DataAssigment;

import java.util.List;

public class AcceptEquitmentAssimentRequest {
    List<DataAssigment> AssignmentDetail;

    public List<DataAssigment> getAssignmentDetail() {
        return AssignmentDetail;
    }

    public void setAssignmentDetail(List<DataAssigment> assignmentDetail) {
        AssignmentDetail = assignmentDetail;
    }

    public String getAssignmentID() {
        return AssignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        AssignmentID = assignmentID;
    }

    private String AssignmentID;
}
