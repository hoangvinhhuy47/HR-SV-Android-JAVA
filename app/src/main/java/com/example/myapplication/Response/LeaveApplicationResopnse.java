package com.example.myapplication.Response;

import com.example.myapplication.Model.DataLeaveAplication;

import java.util.List;

public class LeaveApplicationResopnse extends BaseReponse {
    public List<DataLeaveAplication> getData() {
        return Data;
    }

    public void setData(List<DataLeaveAplication> data) {
        Data = data;
    }

    List<DataLeaveAplication> Data;
}
