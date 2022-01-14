package com.example.myapplication.Response;

import com.example.myapplication.Model.DataSymbol;

import java.util.List;

public class GetDataSymbolResponse extends BaseReponse {
    public List<DataSymbol> getData() {
        return Data;
    }

    public void setData(List<DataSymbol> data) {
        Data = data;
    }

    List<DataSymbol> Data;
}
