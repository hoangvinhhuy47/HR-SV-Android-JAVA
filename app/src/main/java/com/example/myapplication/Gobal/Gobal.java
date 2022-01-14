package com.example.myapplication.Gobal;

import android.app.Application;

import com.example.myapplication.Model.SettingConfig;
import com.example.myapplication.Model.Task;
import com.example.myapplication.Model.User;

import java.util.Date;
import java.util.List;

public class Gobal {
    public static String getWebAPI() {
        return WebAPI;
    }

    public static void setWebAPI(String url) {
        Gobal.WebAPI = url;
    }

    public static int getSiteID() {
        return SiteID;
    }

    public static void setSiteID(int siteID) {
        SiteID = siteID;
    }

    public static String getGuiID() {
        return GuiID;
    }

    public static void setGuiID(String guiID) {
        GuiID = guiID;
    }

    public  static  User getUser(){return User;}
    public  static  void setUser(User user){ User = user;}

    public static String getUserID() {
        return UserID;
    }

    public static void setUserID(String userID) {
        UserID = userID;
    }

    public static String UserID;
    private static String WebAPI;
    private static int SiteID;
    private static String GuiID;
    private  static User User;

    public static String getTaskEmployeeID() {
        return TaskEmployeeID;
    }

    public static void setTaskEmployeeID(String taskEmployeeID) {
        TaskEmployeeID = taskEmployeeID;
    }

    private static String TaskEmployeeID;

    public static int getTotalpage() {
        return Totalpage;
    }

    public static void setTotalpage(int totalpage) {
        Totalpage = totalpage;
    }

    private static int Totalpage;

    public static boolean getIsCheckIn() {
        return IsCheckIn;
    }

    public static void setIsCheckIn(boolean isCheckIn) {
        IsCheckIn = isCheckIn;
    }

    private static boolean IsCheckIn;

    public static String getCheckTime() {
        return CheckTime;
    }

    public static void setCheckTime(String checkTime) {
        CheckTime = checkTime;
    }

    public static String getShiftName() {
        return ShiftName;
    }

    public static void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    private static String CheckTime;
    private static String ShiftName;
    public static String fromdayint;

    public static String getFromdayint() {
        return fromdayint;
    }

    public static void setFromdayint(String fromdayint) {
        Gobal.fromdayint = fromdayint;
    }

    public static String getTodayint() {
        return todayint;
    }

    public static void setTodayint(String todayint) {
        Gobal.todayint = todayint;
    }

    public static String todayint;

}

