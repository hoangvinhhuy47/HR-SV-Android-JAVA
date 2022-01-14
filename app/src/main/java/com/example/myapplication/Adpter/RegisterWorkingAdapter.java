package com.example.myapplication.Adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.InformationRegisterWorking;
import com.example.myapplication.Model.DataLeaveAplication;
import com.example.myapplication.Model.DataRegisterWorking;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RegisterWorkingAdapter extends RecyclerView.Adapter<RegisterWorkingAdapter.MyViewHolder> {
    private Context context;
    private List<DataRegisterWorking> datalist = new ArrayList<>();
    public RegisterWorkingAdapter(List<DataRegisterWorking> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;
    }
    @NonNull
    @Override
    public RegisterWorkingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemregisterworkingadapter, parent, false);
        return new RegisterWorkingAdapter.MyViewHolder(view);
    }
    public void loadmore(List<DataRegisterWorking> lst) {
        datalist.addAll(lst);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterWorkingAdapter.MyViewHolder holder, int position) {
        DataRegisterWorking dataRegisterWorking = datalist.get(position);
        Picasso.with(context);
        for (int i = 0; i <= position; i++) {
            holder.Sttregister.setText(String.valueOf(position + 1));
        }
        holder.todaydataregister.setText(GobalUtils.convertDateTime(GobalUtils.convertStringToDate(dataRegisterWorking.getToDate())));
        holder.fromdaydataregister.setText(GobalUtils.convertDateTime(GobalUtils.convertStringToDate(dataRegisterWorking.getFromDate())));
        holder.statusdataregister.setText(com.example.myapplication.Utils.GobalUtils.convertStatusLeaveAplication(dataRegisterWorking.getStatus()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InformationRegisterWorking.class);
                intent.putExtra("FromdayRegister", GobalUtils.convertDate(GobalUtils.convertStringToDate(dataRegisterWorking.getFromDate())));
                intent.putExtra("TodayRegister", GobalUtils.convertDate(GobalUtils.convertStringToDate(dataRegisterWorking.getToDate())));
                intent.putExtra("FromTimeRegister", GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getFromDate())));
                intent.putExtra("ToTimeRegister", GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getToDate())));
                intent.putExtra("FromdayRegister1", GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getFromDate())));
                intent.putExtra("TodayRegister1", GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getToDate())));
                intent.putExtra("FromTimeRegister1", GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getFromDate())));
                intent.putExtra("ToTimeRegister1", GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataRegisterWorking.getToDate())));
                intent.putExtra("ReasonRegister", dataRegisterWorking.getLeaveReason());
                intent.putExtra("LeaveIDInfo", dataRegisterWorking.getLeaveID());
                intent.putExtra("StatusRegister", String.valueOf(dataRegisterWorking.getStatus()));
                intent.putExtra("ManagerID", dataRegisterWorking.getManagerID());
                intent.putExtra("ActionNotesWorking", dataRegisterWorking.getActionNotes());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        holder.backgroud_item_registerw.setBackground(context.getResources().getDrawable(R.drawable.backgroud_home));

    }

    @Override
    public int getItemCount() {

        return datalist == null ? 0 : datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Sttregister, todaydataregister, fromdaydataregister, statusdataregister;
        CardView container2;
        RelativeLayout backgroud_item_registerw;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Sttregister = itemView.findViewById(R.id.Sttregister);
            todaydataregister = itemView.findViewById(R.id.todaydataregister);
            fromdaydataregister = itemView.findViewById(R.id.fromdaydataregister);
            statusdataregister = itemView.findViewById(R.id.statusdataregister);
            container2 = itemView.findViewById(R.id.container2);
            backgroud_item_registerw = itemView.findViewById(R.id.backgroud_item_registerw);
        }
    }
}
