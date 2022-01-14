package com.example.myapplication.Response;

import com.example.myapplication.Model.Task;

import java.util.List;

public class TaskReponse extends BaseReponse {


    public List<Task> getData() {
        return Data;
    }

    public void setData(List<Task> data) {
        Data = data;
    }

    private List<Task> Data;


}
