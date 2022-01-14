package com.example.myapplication.Activity;

import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SalaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.acitivity_salary);
        super.onCreate(savedInstanceState);
        TimePicker picker=(TimePicker)findViewById(R.id.datePicker1);
        picker.setIs24HourView(true);
    }
}
