package com.example.myapplication.Model;

public class DataTaskEmployee {
    public String TaskEmployeeID;

    public String getTaskEmployeeID() {
        return TaskEmployeeID;
    }

    public void setTaskEmployeeID(String taskEmployeeID) {
        TaskEmployeeID = taskEmployeeID;
    }

    public int getFinalPercent() {
        return FinalPercent;
    }

    public void setFinalPercent(int finalPercent) {
        FinalPercent = finalPercent;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public int FinalPercent;
    public String Note;
}
