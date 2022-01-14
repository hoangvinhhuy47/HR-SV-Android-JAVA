package com.example.myapplication.Model;

import java.util.Date;

public class DataCheckIn {
    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String checkTime) {
        CheckTime = checkTime;
    }

    private String CheckTime;

    public String getShiftName() {
        return ShiftName;
    }

    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    public boolean isCheckIn() {
        return IsCheckIn;
    }

    public void setCheckIn(boolean checkIn) {
        IsCheckIn = checkIn;
    }

    public String getDateTimeOut() {
        return DateTimeOut;
    }

    public void setDateTimeOut(String dateTimeOut) {
        DateTimeOut = dateTimeOut;
    }

    public String getDateTimeIn() {
        return DateTimeIn;
    }

    public void setDateTimeIn(String dateTimeIn) {
        DateTimeIn = dateTimeIn;
    }

    private String ShiftName;
    private boolean IsCheckIn;
    private String DateTimeOut;
    private String DateTimeIn;
}
