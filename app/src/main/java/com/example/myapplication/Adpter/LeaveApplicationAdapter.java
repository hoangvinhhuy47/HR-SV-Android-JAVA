package com.example.myapplication.Adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Activity.InfomationLeaveAplication;
import com.example.myapplication.Model.DataLeaveAplication;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static java.lang.Integer.valueOf;

public class LeaveApplicationAdapter extends RecyclerView.Adapter<LeaveApplicationAdapter.MyViewHolder> {
    private Context context;

    private List<DataLeaveAplication> datalist = new ArrayList<>();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public LeaveApplicationAdapter(List<DataLeaveAplication> datalist, Context context) {
        this.datalist.addAll(datalist);
        this.context = context;
    }

    @NonNull
    @Override
    public LeaveApplicationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acitivity_itemleaveapplication, parent, false);
        return new LeaveApplicationAdapter.MyViewHolder(view);
    }
    public void loadmore(List<DataLeaveAplication> lst){
        datalist.addAll(lst);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull LeaveApplicationAdapter.MyViewHolder holder, int position) {
        DataLeaveAplication dataLeaveAplication = datalist.get(position);
        Picasso.with(context);
        holder.backgroud_item_leave.setBackground(context.getResources().getDrawable(R.drawable.backgroud_home));
        for (int i = 0; i <= position; i++) {
            holder.STT.setText(String.valueOf(i+1));
        }
        holder.TodayleaveIT.setText(GobalUtils.convertDateTime(GobalUtils.convertStringToDate(dataLeaveAplication.getToDate())));
        holder.FromdayleaveIT.setText(GobalUtils.convertDateTime(GobalUtils.convertStringToDate(dataLeaveAplication.getFromDate())));
        holder.Status.setText(com.example.myapplication.Utils.GobalUtils.convertStatusLeaveAplication(dataLeaveAplication.getStatus()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfomationLeaveAplication.class);
                    intent.putExtra("FromDay",GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataLeaveAplication.getFromDate())));
                    intent.putExtra("FromDay1",GobalUtils.convertDate(GobalUtils.convertStringToDate(dataLeaveAplication.getFromDate())));
                    intent.putExtra("FromTime",GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataLeaveAplication.getFromDate())));
                    intent.putExtra("ToDay",GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataLeaveAplication.getToDate())));
                    intent.putExtra("ToDay1",GobalUtils.convertDate(GobalUtils.convertStringToDate(dataLeaveAplication.getToDate())));
                    intent.putExtra("ToTime",GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(dataLeaveAplication.getToDate())));
                    intent.putExtra("LeaveReson",dataLeaveAplication.getLeaveReason());
                    intent.putExtra("LeaveID",dataLeaveAplication.getLeaveID());
                    intent.putExtra("StatusLeaveApplication",String.valueOf(dataLeaveAplication.getStatus()));
                    intent.putExtra("ManagerID",dataLeaveAplication.getManagerID());
                    intent.putExtra("ActionNotes",dataLeaveAplication.getActionNotes());
                    intent.putExtra("IDSymbol",dataLeaveAplication.getSymbolID());
//                    intent.putExtra("Position",position);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }

    @Override
    public int getItemCount() {
        return datalist == null ? 0 : datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView STT, TodayleaveIT, FromdayleaveIT, Status;
        CardView container;
        RelativeLayout backgroud_item_leave;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            STT = itemView.findViewById(R.id.STT);
            TodayleaveIT = itemView.findViewById(R.id.todayleaveIT);
            FromdayleaveIT = itemView.findViewById(R.id.fromdayleaveIT);
            Status = itemView.findViewById(R.id.status);
            container = itemView.findViewById(R.id.container);
            backgroud_item_leave = itemView.findViewById(R.id.backgroud_item_leave);
        }
    }


}
