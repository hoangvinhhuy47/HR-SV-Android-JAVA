package com.example.myapplication.Request;

import com.example.myapplication.Model.DataTaskEmployee;

public class UpdatePercentRequest  {
    public DataTaskEmployee getTaskEmployee() {
        return TaskEmployee;
    }

    public void setTaskEmployeeID(DataTaskEmployee taskEmployee) {
        TaskEmployee = taskEmployee;
    }

    DataTaskEmployee TaskEmployee;
}
