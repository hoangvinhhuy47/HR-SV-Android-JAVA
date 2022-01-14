package com.example.myapplication.Model;

public class DataManager {
    public int SiteID;
    public String EmployeeID;

    public int getSiteID() {
        return SiteID;
    }

    public void setSiteID(int siteID) {
        SiteID = siteID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String managerName) {
        ManagerName = managerName;
    }

    public String ManagerID;
    public String ManagerName;
    @Override
    public String toString(){
        return this.getManagerName();
    }
}
