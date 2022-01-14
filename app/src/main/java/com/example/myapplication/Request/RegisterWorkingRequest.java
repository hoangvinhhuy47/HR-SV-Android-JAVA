package com.example.myapplication.Request;

import com.example.myapplication.Model.DataRegisterWorking;

public class RegisterWorkingRequest {
    public String getManagerID() {
        return ManagerID;
    }

    public void setManagerID(String managerID) {
        ManagerID = managerID;
    }

    private String ManagerID;
    private String EmployeeID;

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    private String FromDate;
    private String ToDate;

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    private int PageNumber;

    public String getSymbolID() {
        return SymbolID;
    }

    public void setSymbolID(String symbolID) {
        SymbolID = symbolID;
    }

    private String SymbolID;
}
