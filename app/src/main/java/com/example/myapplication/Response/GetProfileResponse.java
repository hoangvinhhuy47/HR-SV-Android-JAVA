package com.example.myapplication.Response;

import com.example.myapplication.Model.DataGetProfile;

public class GetProfileResponse extends BaseReponse {
    public DataGetProfile getData() {
        return Data;
    }

    public void setData(DataGetProfile data) {
        Data = data;
    }

    DataGetProfile Data;
}