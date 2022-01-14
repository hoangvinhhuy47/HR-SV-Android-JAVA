package com.example.myapplication.Adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ImfomationEquitmentActivity;
import com.example.myapplication.Model.DataEquipmentEmployee;
import com.example.myapplication.Model.DataEquipmentRecovery;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;

import java.util.ArrayList;
import java.util.List;

public class EquitmentRecoveryAdapter extends RecyclerView.Adapter<EquitmentRecoveryAdapter.MyViewHolder> {
    List<DataEquipmentRecovery> datalist = new ArrayList<>();
    Context context;
    public EquitmentRecoveryAdapter(List<DataEquipmentRecovery> datalist1, Context context){
        this.datalist.addAll(datalist1);
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equitment_recovery, parent, false);
        return new EquitmentRecoveryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataEquipmentRecovery db = datalist.get(position);
        holder.stt_equitment.setText(String.valueOf(position+1));

        holder.status_equitment.setText(String.valueOf(position+1));
        holder.formday_equitment.setText(GobalUtils.convertDateTime(GobalUtils.convertStringToDate(db.getCreatedDate())));
        holder.status_equitment.setText(GobalUtils.ConverSttEquitment(db.getStatus()));
        holder.name_equitment.setText(db.getFullName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImfomationEquitmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ID","1");
                intent.putExtra("RecoveryID",db.getRecoveryID());
                context.startActivity(intent);
            }
        });
        holder.backgroud_item_equiment_re.setBackground(context.getResources().getDrawable(R.drawable.backgroud_home));

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stt_equitment,formday_equitment,name_equitment,status_equitment;
        RelativeLayout backgroud_item_equiment_re;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stt_equitment= itemView.findViewById(R.id.stt_equitment);
            formday_equitment= itemView.findViewById(R.id.formday_equitment);
            name_equitment= itemView.findViewById(R.id.name_equitment);
            status_equitment= itemView.findViewById(R.id.status_equitment);
            backgroud_item_equiment_re = itemView.findViewById(R.id.backgroud_item_equiment_re);
        }
    }
}
