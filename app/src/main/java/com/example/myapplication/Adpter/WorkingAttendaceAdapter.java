package com.example.myapplication.Adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.DataWorkingAttendace;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkingAttendaceAdapter extends RecyclerView.Adapter<WorkingAttendaceAdapter.MyViewHolder> {
    private Context context;
    private List<DataWorkingAttendace> Datalist = new ArrayList<>();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public WorkingAttendaceAdapter(List<DataWorkingAttendace> Datalist, Context context) {
        this.Datalist.addAll(Datalist);
        this.context = context;
    }

    @NonNull
    @Override
    public WorkingAttendaceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemdataworkingattendace, parent, false);
        return new WorkingAttendaceAdapter.MyViewHolder(view);

    }
public void loadmore(List<DataWorkingAttendace> lst){
        Datalist.addAll(lst);
        notifyDataSetChanged();
}
    @Override
    public void onBindViewHolder(@NonNull WorkingAttendaceAdapter.MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            DataWorkingAttendace dataWorkingAttendace = Datalist.get(position);
            Picasso.with(context);
            if (position % 2 !=0){
                holder.card_1.setCardBackgroundColor(Color.parseColor("#ffffff"));
//                holder.btndateworking.setBackgroundColor(Color.parseColor("#c8e6c9"));
            }else {
                holder.card_1.setCardBackgroundColor(Color.parseColor("#c8e6c9"));
            }
            String a= GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataWorkingAttendace.getWorkingDate()));
            holder.btndateworking.setText(GobalUtils.DateEtoDateVN(GobalUtils.coverStringToDateOfWeek(GobalUtils.convertStringToDate(dataWorkingAttendace.getWorkingDate()))) +"\n" +a);
            holder.txtshiftnameworking.setText(dataWorkingAttendace.getShiftName());
            holder.txtworkingtime.setText(dataWorkingAttendace.getWorkingTime());
            holder.txttimetIN.setText(dataWorkingAttendace.getAttendanceTime());

        }
    }

    @Override
    public int getItemCount() {
        return Datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button btndateworking;
        CardView card_1;
        public TextView txtshiftnameworking, txtworkingtime, txttimetIN;
        GridLayout attendace;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btndateworking = itemView.findViewById(R.id.dateworking);
            txtshiftnameworking = itemView.findViewById(R.id.shiftnameworking);
            txtworkingtime = itemView.findViewById(R.id.workingtime);
            txttimetIN = itemView.findViewById(R.id.timetIN);
            attendace = itemView.findViewById(R.id.attendace);
            card_1 = itemView.findViewById(R.id.card_1);



        }
    }
}
