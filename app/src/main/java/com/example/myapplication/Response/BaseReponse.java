package com.example.myapplication.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseReponse {
    @SerializedName("StatusID")
    @Expose
    public int StatusID;
    @SerializedName("TotalPage")
    @Expose
    public int TotalPage;
    @SerializedName("ErrorCode")
    @Expose
    public String ErrorCode;
    @SerializedName("ErrorDescription")
    @Expose
    public String ErrorDescription;

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorDescription() {
        return ErrorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        ErrorDescription = errorDescription;
    }
    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int totalPage) {
        TotalPage = totalPage;
    }


}
