package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.ImfomationEquitmentAdapter;
import com.example.myapplication.Fragment.EquitmentAssigment;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataAllEquipmentAssignmentInfo;
import com.example.myapplication.Model.DataAllEquipmentRecoveryInfo;
import com.example.myapplication.Model.DataEquipmentEmployee;
import com.example.myapplication.Model.DataEquipmentRecovery;
import com.example.myapplication.Model.DataEquipmentRecoveryDetail;
import com.example.myapplication.Model.DataEquitmentDetail;
import com.example.myapplication.R;
import com.example.myapplication.Request.AcceptEquipmentRecoveryRequest;
import com.example.myapplication.Request.AcceptEquitmentAssimentRequest;
import com.example.myapplication.Request.GetAllEquipmentAssignmentInfoRequest;
import com.example.myapplication.Request.GetAllEquipmentRecoveryInfoRequest;
import com.example.myapplication.Response.AcceptEquipmentRecoveryResponse;
import com.example.myapplication.Response.AcceptEquitmentAssimentResponse;
import com.example.myapplication.Response.GetAllEquipmentAssignmentInfoResponse;
import com.example.myapplication.Response.GetAllEquipmentRecoveryInfoResponse;
import com.example.myapplication.Utils.GobalUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImfomationEquitmentActivity extends AppCompatActivity {
    List<DataAllEquipmentAssignmentInfo> datalist = new ArrayList<>();
    ImfomationEquitmentAdapter imfomationEquitmentAdapter;
    TextView note_infoequitment, fromday_info_equitment, name_assigment, name_customer;
    RecyclerView recy_infoequitment;
    LinearLayoutManager manager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
    Button btnD, btnDY;
    List<DataEquipmentRecoveryDetail> lstrecovery = new ArrayList<>();
    List<DataEquitmentDetail> dataEquitmentDetails = new ArrayList<>();
    List<DataAllEquipmentRecoveryInfo> datalist1 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imfomationequitment);
        note_infoequitment = findViewById(R.id.note_infoequitment);
        fromday_info_equitment = findViewById(R.id.fromday_info_equitment);
        name_assigment = findViewById(R.id.name_assigment);
        name_customer = findViewById(R.id.name_customer);
        recy_infoequitment = findViewById(R.id.recy_infoequitment);
        btnDY = findViewById(R.id.btnDY);

        btnD = findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = this.getIntent();
        if (intent.getStringExtra("ID").equals("2") == true) {
            GetAllEquipmentAssignmentInfoRequest getAllEquipmentAssignmentInfoRequest = new GetAllEquipmentAssignmentInfoRequest();
            getAllEquipmentAssignmentInfoRequest.setAssignmentID(intent.getStringExtra("AssignmentID"));
            GetAllEquipmentAssignmentInfo(getAllEquipmentAssignmentInfoRequest);
        }
        if (intent.getStringExtra("ID").equals("1") == true) {
            GetAllEquipmentRecoveryInfoRequest getAllEquipmentRecoveryInfoRequest = new GetAllEquipmentRecoveryInfoRequest();
            getAllEquipmentRecoveryInfoRequest.setRecoveryID(intent.getStringExtra("RecoveryID"));
            GetEquipmentRecoveryInfo(getAllEquipmentRecoveryInfoRequest);
        }


    }

    public void GetEquipmentRecoveryInfo(GetAllEquipmentRecoveryInfoRequest getAllEquipmentRecoveryInfoRequest) {
        Call<GetAllEquipmentRecoveryInfoResponse> getAllEquipmentRecoveryInfoResponseCall = APIClient.getAllEquipmentReCoveryInfoService().GetEquipmentRecoveryInfo(Gobal.getGuiID(), getAllEquipmentRecoveryInfoRequest);
        getAllEquipmentRecoveryInfoResponseCall.enqueue(new Callback<GetAllEquipmentRecoveryInfoResponse>() {
            @Override
            public void onResponse(Call<GetAllEquipmentRecoveryInfoResponse> call, Response<GetAllEquipmentRecoveryInfoResponse> response) {
                if (response.isSuccessful()) {
                    GetAllEquipmentRecoveryInfoResponse getAllEquipmentRecoveryInfoResponse = response.body();
                    if (getAllEquipmentRecoveryInfoResponse.getStatusID() == 1) {
                        try {
                            name_customer.setText(getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getFullName());
                            name_assigment.setText(getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getAssignmentEmployeeName());
                            note_infoequitment.setText(getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getNotes());
                            fromday_info_equitment.setText(GobalUtils.convertDateShowScreen2(GobalUtils.convertStringToDate(getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getCreatedDate())));
                            imfomationEquitmentAdapter = new ImfomationEquitmentAdapter(dataEquitmentDetails, getAllEquipmentRecoveryInfoResponse.getData().getRecoveryDetails(), getApplicationContext(), 2);
                            recy_infoequitment.setAdapter(imfomationEquitmentAdapter);
                            recy_infoequitment.setLayoutManager(manager);
                            if (getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getStatus() != 1) {
                                btnDY.setFocusable(false);
                            } else {
                                btnDY.setFocusable(true);
                                btnDY.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AcceptEquipmentRecoveryRequest acceptEquipmentRecoveryRequest = new AcceptEquipmentRecoveryRequest();
                                        acceptEquipmentRecoveryRequest.setRecoveryID(getAllEquipmentRecoveryInfoResponse.getData().getRecovery().getRecoveryID());
                                        AcceptEquipmentRecovery(acceptEquipmentRecoveryRequest);
                                    }
                                });
                            }


                        } catch (Exception e) {

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllEquipmentRecoveryInfoResponse> call, Throwable t) {

            }
        });
    }

    public void GetAllEquipmentAssignmentInfo(GetAllEquipmentAssignmentInfoRequest getAllEquipmentAssignmentInfoRequest) {
        Call<GetAllEquipmentAssignmentInfoResponse> getAllEquipmentAssignmentInfoResponseCall = APIClient.getAllEquipmentAssignmentInfoService().GetAllEquipmentAssignmentInfo(Gobal.getGuiID(), getAllEquipmentAssignmentInfoRequest);
        getAllEquipmentAssignmentInfoResponseCall.enqueue(new Callback<GetAllEquipmentAssignmentInfoResponse>() {
            @Override
            public void onResponse(Call<GetAllEquipmentAssignmentInfoResponse> call, Response<GetAllEquipmentAssignmentInfoResponse> response) {
                if (response.isSuccessful()) ;
                GetAllEquipmentAssignmentInfoResponse getAllEquipmentAssignmentInfoResponse = response.body();
                if (getAllEquipmentAssignmentInfoResponse.getStatusID() == 1) {
                    try {
                        name_customer.setText(getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getFullName());
                        name_assigment.setText(getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getAssignmentEmployeeName());
                        note_infoequitment.setText(getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getNotes());
                        fromday_info_equitment.setText(GobalUtils.convertDateShowScreen2(GobalUtils.convertStringToDate(getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getCreatedDate())));
                        imfomationEquitmentAdapter = new ImfomationEquitmentAdapter(getAllEquipmentAssignmentInfoResponse.getData().getAssignmentDetails(), lstrecovery, getApplicationContext(), 1);
                        recy_infoequitment.setAdapter(imfomationEquitmentAdapter);
                        recy_infoequitment.setLayoutManager(manager);
                        if (getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getStatus() != 1) {
                            btnDY.setFocusable(false);
                        } else {
                            btnDY.setFocusable(true);
                            btnDY.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AcceptEquitmentAssimentRequest acceptEquitmentAssimentRequest = new AcceptEquitmentAssimentRequest();
                                    acceptEquitmentAssimentRequest.setAssignmentID(getAllEquipmentAssignmentInfoResponse.getData().getAssignment().getAssignmentID());
                                    acceptEquitmentAssimentRequest.setAssignmentDetail(EquitmentAssigment.GobalEquitment.getDataAssigments());
                                    AcceptEquitmentAssiment(acceptEquitmentAssimentRequest);
                                }
                            });
                        }


                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllEquipmentAssignmentInfoResponse> call, Throwable t) {

            }
        });
    }

    public void AcceptEquitmentAssiment(AcceptEquitmentAssimentRequest acceptEquitmentAssimentRequest) {
        Call<AcceptEquitmentAssimentResponse> acceptEquitmentAssimentResponseCall = APIClient.acceptEquitmentAssimentService().AcceptEquitmentAssimentResponse(Gobal.getGuiID(), acceptEquitmentAssimentRequest);
        acceptEquitmentAssimentResponseCall.enqueue(new Callback<AcceptEquitmentAssimentResponse>() {
            @Override
            public void onResponse(Call<AcceptEquitmentAssimentResponse> call, Response<AcceptEquitmentAssimentResponse> response) {
                if (response.isSuccessful()) {
                    AcceptEquitmentAssimentResponse acceptEquitmentAssimentResponse = response.body();
                    if (acceptEquitmentAssimentResponse.getStatusID() == 1) {
                        Toast.makeText(getApplication(), "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "Cập Nhật Không Thành Công", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AcceptEquitmentAssimentResponse> call, Throwable t) {

            }
        });
    }

    public void AcceptEquipmentRecovery(AcceptEquipmentRecoveryRequest acceptEquipmentRecoveryRequest) {
        Call<AcceptEquipmentRecoveryResponse> acceptEquipmentRecoveryResponseCall = APIClient.acceptEquipmentRecoverySerVice().AcceptEquipmentRecovery(Gobal.getGuiID(), acceptEquipmentRecoveryRequest);
        acceptEquipmentRecoveryResponseCall.enqueue(new Callback<AcceptEquipmentRecoveryResponse>() {
            @Override
            public void onResponse(Call<AcceptEquipmentRecoveryResponse> call, Response<AcceptEquipmentRecoveryResponse> response) {
                if (response.isSuccessful()) {
                    AcceptEquipmentRecoveryResponse acceptEquipmentRecoveryResponse = response.body();
                    if (acceptEquipmentRecoveryResponse.getStatusID() == 1) {
                        Toast.makeText(getApplication(), "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "Cập Nhật Không Thành Công", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AcceptEquipmentRecoveryResponse> call, Throwable t) {
                Toast.makeText(getApplication(), "Lỗi Kết Nối, Vui Lòng  Thử Lại Sau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
