package com.example.myapplication.Adpter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.ImfomationEquitmentActivity;
import com.example.myapplication.Fragment.EquitmentAssigment;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataAllEquipmentAssignmentInfo;
import com.example.myapplication.Model.DataAssigment;
import com.example.myapplication.Model.DataEquipmentRecoveryDetail;
import com.example.myapplication.Model.DataEquitmentDetail;
import com.example.myapplication.R;
import com.example.myapplication.Utils.GobalUtils;

import java.util.ArrayList;
import java.util.List;

public class ImfomationEquitmentAdapter extends RecyclerView.Adapter<ImfomationEquitmentAdapter.MyViewHolder> {
    List<DataEquitmentDetail> datalist = new ArrayList<>();
    List<DataEquipmentRecoveryDetail> lstrecovery = new ArrayList<>();
    int ID;
    Context context;
    DataAssigment dataAssigment;

    public ImfomationEquitmentAdapter(List<DataEquitmentDetail> datalist, List<DataEquipmentRecoveryDetail> lstrecovery, Context context, int ID) {
        this.datalist.addAll(datalist);
        this.context = context;
        this.lstrecovery.addAll(lstrecovery);
        this.ID = ID;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itemimfomationequitement, parent, false);
        return new ImfomationEquitmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stt_imfomation_equitment.setText(String.valueOf(position + 1));
        if (ID == 1) {
            holder.remove_equiment.setVisibility(View.VISIBLE);
            DataEquitmentDetail db = datalist.get(position);
            EquitmentAssigment.GobalEquitment.dataAssigments1 = new ArrayList<>();
            for (int i = 0; i < datalist.size(); i++) {
                dataAssigment = new DataAssigment();
                dataAssigment.setStatus(String.valueOf(datalist.get(i).getStatus()));
                dataAssigment.setDetailID(String.valueOf(datalist.get(i).getDetailID()));
                dataAssigment.setReasonDeny(String.valueOf(datalist.get(i).getReasonDeny()));
                EquitmentAssigment.GobalEquitment.dataAssigments1.add(i, dataAssigment);
            }
            holder.code_imfomation_equitment.setText(db.getEquipmentCode());
            holder.name_imfomation_equitment.setText(db.getEquipmentName());
            holder.status_imfomation_equitment.setText(GobalUtils.ConverSttEquitment(db.getStatus()));
            if (db.getStatus() != 1) {
                holder.remove_equiment.setFocusable(false);
            } else {
                holder.remove_equiment.setFocusable(true);
                holder.remove_equiment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog1 = new Dialog(v.getContext());
                        dialog1.setContentView(R.layout.dialog_remove_equitment);
                        dialog1.getWindow().setGravity(Gravity.CENTER);
                        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dialog1.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog1.show();
                        TextView equitmentnamedialog = dialog1.findViewById(R.id.equitmentnamedialog);
                        Button btnAccept = dialog1.findViewById(R.id.accept);
                        Button btnClose = dialog1.findViewById(R.id.close);
                        ImageView imgexit = dialog1.findViewById(R.id.imgexit);
                        EditText reasonforrefusal = dialog1.findViewById(R.id.reasonforrefusal);
                        equitmentnamedialog.setText(db.getEquipmentName());
                        reasonforrefusal.setText(db.getReasonDeny());
                        btnAccept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dataAssigment.setReasonDeny(reasonforrefusal.getText().toString());
                                dataAssigment.setStatus("3");
                                dataAssigment.setDetailID(db.getDetailID());
                                EquitmentAssigment.GobalEquitment.getDataAssigments().remove(position);
                                EquitmentAssigment.GobalEquitment.getDataAssigments().add(position, dataAssigment);
                                dialog1.dismiss();
                                Toast.makeText(context, "Hệ Thống Đã Ghi Nhận Câu Trả Lời Của Bạn", Toast.LENGTH_SHORT).show();
                            }
                        });
                        imgexit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();
                            }
                        });
                        btnClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });
            }
        }
        if (ID == 2) {
            DataEquipmentRecoveryDetail db = lstrecovery.get(position);
            holder.stt_imfomation_equitment.setText(String.valueOf(position + 1));
            holder.code_imfomation_equitment.setText(db.getEquipmentCode());
            holder.name_imfomation_equitment.setText(db.getEquipmentName());
            holder.status_imfomation_equitment.setText(GobalUtils.ConverSttEquitment(db.getStatus()));
            if (position % 2 == 0) {
                holder.container2.setCardBackgroundColor(Color.parseColor("#c8e6c9"));
            } else {
                holder.container2.setCardBackgroundColor(Color.parseColor("#F0F3D7"));
            }
            holder.remove_equiment.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        if (datalist.size() != 0) {
            return datalist.size();
        } else {
            return lstrecovery.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stt_imfomation_equitment, code_imfomation_equitment, name_imfomation_equitment, status_imfomation_equitment;
        CardView container2;
        Button remove_equiment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            status_imfomation_equitment = itemView.findViewById(R.id.status_imfomation_equitment);
            stt_imfomation_equitment = itemView.findViewById(R.id.stt_imfomation_equitment);
            code_imfomation_equitment = itemView.findViewById(R.id.code_imfomation_equitment);
            name_imfomation_equitment = itemView.findViewById(R.id.name_imfomation_equitment);
            container2 = itemView.findViewById(R.id.container2);
            remove_equiment = itemView.findViewById(R.id.remove_equiment);
        }
    }
}
