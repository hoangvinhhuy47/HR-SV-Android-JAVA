package com.example.myapplication.Model;

import java.util.List;

public class DataAllEquipmentAssignmentInfo {
    public DataEquipmentEmployee getAssignment() {
        return Assignment;
    }

    public void setAssignment(DataEquipmentEmployee assignment) {
        Assignment = assignment;
    }

    DataEquipmentEmployee Assignment;



    public List<DataEquitmentDetail> getAssignmentDetails() {
        return AssignmentDetails;
    }

    public void setAssignmentDetails(List<DataEquitmentDetail> assignmentDetails) {
        AssignmentDetails = assignmentDetails;
    }

        List<DataEquitmentDetail> AssignmentDetails;
}
