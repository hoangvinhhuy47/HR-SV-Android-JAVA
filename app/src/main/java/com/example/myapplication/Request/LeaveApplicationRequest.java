package com.example.myapplication.Request;

public class LeaveApplicationRequest  {
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

    public String SymbolID;

}
