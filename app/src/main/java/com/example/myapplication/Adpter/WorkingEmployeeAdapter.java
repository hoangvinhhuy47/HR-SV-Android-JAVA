package com.example.myapplication.Adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.DataWorkingAttendace;
import com.example.myapplication.Model.DataWorkingEmployee;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class  WorkingEmployeeAdapter extends RecyclerView.Adapter<WorkingEmployeeAdapter.MyViewHolder> {
    private Context context;
    private List<DataWorkingEmployee> Datalist = new ArrayList<>();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public WorkingEmployeeAdapter(List<DataWorkingEmployee> Datalist, Context context) {
        this.Datalist.addAll(Datalist);
        this.context = context;
    }
    @NonNull
    @Override
    public WorkingEmployeeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemdataworkingemployee, parent, false);
        return new WorkingEmployeeAdapter.MyViewHolder(view);
    }
    public void loadmore(List<DataWorkingEmployee> lst){
        Datalist.addAll(lst);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull WorkingEmployeeAdapter.MyViewHolder holder, int position) {
        DataWorkingEmployee dataWorkingEmployee = Datalist.get(position);
        Picasso.with(context);
        String a= GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(dataWorkingEmployee.getWorkingDate()));
        holder.btndateworkingE.setText(GobalUtils.DateEtoDateVN(GobalUtils.coverStringToDateOfWeek(GobalUtils.convertStringToDate(dataWorkingEmployee.getWorkingDate()))) +"\n" +a);
        holder.txttimework.setText(String.valueOf(dataWorkingEmployee.getW()));
        holder.txttimeleaveP.setText(String.valueOf(dataWorkingEmployee.getP()));
        holder.txttimeleaveN.setText(String.valueOf(dataWorkingEmployee.getK()));
        holder.txttimeOT1.setText(String.valueOf(dataWorkingEmployee.getOT1()));
        holder.txttimeOT150.setText(String.valueOf(dataWorkingEmployee.getOT150()));
        holder.txttimeOTNight.setText(String.valueOf(dataWorkingEmployee.getOTNight()));
    }

    @Override
    public int getItemCount() {
        return Datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btndateworkingE;
        TextView txttimework,txttimeleaveP,txttimeleaveN,txttimeOT1,txttimeOT150,txttimeOTNight;
        GridLayout employee;
        CardView Card_2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            employee = itemView.findViewById(R.id.employee);
            btndateworkingE=itemView.findViewById(R.id.dateworkingE);
            txttimework=itemView.findViewById(R.id.timework);
            txttimeleaveP=itemView.findViewById(R.id.timeleaveP);
            txttimeleaveN=itemView.findViewById(R.id.timeleaveN);
            txttimeOT1= itemView.findViewById(R.id.timeOT1);
            txttimeOT150=itemView.findViewById(R.id.timeOT150);
            txttimeOTNight=itemView.findViewById(R.id.timeOTNight);
            Card_2 = itemView.findViewById(R.id.Card_2);
        }
    }
}
