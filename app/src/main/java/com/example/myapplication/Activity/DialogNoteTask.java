package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DialogNoteTask extends AppCompatActivity {
    TextView txttaskname,txtdetailtask;
    Button btnaccept,btnclose;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notetask);
//        txttaskname = findViewById(R.id.taskname);
        txtdetailtask=findViewById(R.id.detailtask);
        btnaccept= findViewById(R.id.accept);
        btnclose=findViewById(R.id.close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
