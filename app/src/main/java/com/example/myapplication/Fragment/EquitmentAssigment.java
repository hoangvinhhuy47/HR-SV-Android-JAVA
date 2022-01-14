package com.example.myapplication.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.EquitmentAdapter;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataAssigment;
import com.example.myapplication.Model.DataEquipmentEmployee;
import com.example.myapplication.R;
import com.example.myapplication.Request.AcceptEquipmentRecoveryRequest;
import com.example.myapplication.Request.GetDataEquipmentAssimentRequest;
import com.example.myapplication.Response.AcceptEquipmentRecoveryResponse;
import com.example.myapplication.Response.GetDataEquipmentAssigmentResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EquitmentAssigment extends AppCompatActivity {
    List<DataEquipmentEmployee> datalist = new ArrayList<>();
    RecyclerView recy_equitment;
    TextView fromday_equitment,today_equitment,stt_equitment, content_equiment;
    Button btnsearch_equitment;
    ImageView back_equitment_r;
    SimpleDateFormat simpleDateFormat1;
    Calendar calendar;
    SimpleDateFormat abc1;
    GobalEquitment gobalEquitment;
    DBHelper dbHelper;
    EquitmentAdapter equitmentAdapter;
    LinearLayoutManager manager ;
    int pagenumber = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_equitment_recovery);
        gobalEquitment = new GobalEquitment();
        manager= new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        dbHelper = new DBHelper(getApplicationContext());
        gobalEquitment.setUserName(Gobal.getUserID());
        recy_equitment = findViewById(R.id.recy_equitment);
        fromday_equitment=findViewById(R.id.fromday_equitment);
        today_equitment=findViewById(R.id.today_equitment);
        stt_equitment=findViewById(R.id.stt_equitment);
        btnsearch_equitment=findViewById(R.id.btnsearch_equitment);
        back_equitment_r = findViewById(R.id.back_equitment_r);
        content_equiment = findViewById(R.id.content_equiment);
        content_equiment.setText("Lịch tiếp nhận thiết bị");
        back_equitment_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        String a = simpleDateFormat.format(calendar.getTime());
        gobalEquitment.setTodayfr(simpleDateFormat1.format(calendar.getTime()));
        today_equitment.setText(a);
        ///
        SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
        abc1 = new SimpleDateFormat("yyyy/MM/01");
        String b = abc.format(calendar.getTime());
        gobalEquitment.setFromdayfr(abc1.format(calendar.getTime()));
        fromday_equitment.setText(b);

        ///
        today_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getApplication(),new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            today_equitment.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            today_equitment.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            today_equitment.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            today_equitment.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        ///
        fromday_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(getApplication(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
//                        Date a =  new Date(selectedyear,selectedmonth,selectedday);
//                        if (a.before(calendar.getTime())){
//                            Toast.makeText(getApplication(),"Oke",Toast.LENGTH_LONG).show();
//                        }
//                        else {
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            fromday_equitment.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromday_equitment.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromday_equitment.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromday_equitment.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEquitment.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        ///
        gobalEquitment.setStatus("-1");
        stt_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplication(), stt_equitment);
                popupMenu.inflate(R.menu.option_menuquitment);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.all_equitment:
                                stt_equitment.setText("Tất Cả");
                                gobalEquitment.setStatus("-1");
                                return false;
                            case R.id.wait_equitment:
                                stt_equitment.setText("Chờ Xác Nhận");
                                gobalEquitment.setStatus("1");
                                return false;
                            case R.id.received_equitment:
                                stt_equitment.setText("Đã Nhận");
                                gobalEquitment.setStatus("2");
                                return  false;
                            case R.id.received1_equitment:
                                stt_equitment.setText("Nhận Một Phần");
                                gobalEquitment.setStatus("3");
                                return false;
                            case R.id.refuse_equitment:
                                stt_equitment.setText("Từ Chối");
                                gobalEquitment.setStatus("4");
                                return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        btnsearch_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber =1;
                GetDataEquipmentAssimentRequest getDataEquipmentAssimentRequest = new GetDataEquipmentAssimentRequest();
                getDataEquipmentAssimentRequest.setPageNumber(String.valueOf(pagenumber));
                getDataEquipmentAssimentRequest.setFromDate(gobalEquitment.getFromdayfr());
                getDataEquipmentAssimentRequest.setToDate(gobalEquitment.getTodayfr());
                getDataEquipmentAssimentRequest.setUserID(gobalEquitment.getUserName());
                getDataEquipmentAssimentRequest.setStatus(gobalEquitment.getStatus());
                GetDataEquipmentEmployee(getDataEquipmentAssimentRequest);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        GetDataEquipmentAssimentRequest getDataEquipmentAssimentRequest = new GetDataEquipmentAssimentRequest();
        getDataEquipmentAssimentRequest.setPageNumber(String.valueOf(pagenumber));
        getDataEquipmentAssimentRequest.setFromDate(gobalEquitment.getFromdayfr());
        getDataEquipmentAssimentRequest.setToDate(gobalEquitment.getTodayfr());
        getDataEquipmentAssimentRequest.setUserID(gobalEquitment.getUserName());
        getDataEquipmentAssimentRequest.setStatus(gobalEquitment.getStatus());
        GetDataEquipmentEmployee(getDataEquipmentAssimentRequest);
    }

    public void GetDataEquipmentEmployee(GetDataEquipmentAssimentRequest getDataEquipmentAssimentRequest){
        Call<GetDataEquipmentAssigmentResponse> getDataEquipmentEmployeeResponseCall = APIClient.getDataEquipmentEmployeeService().GetDataEquipmentEmployee(Gobal.getGuiID(), getDataEquipmentAssimentRequest);
        getDataEquipmentEmployeeResponseCall.enqueue(new Callback<GetDataEquipmentAssigmentResponse>() {
            @Override
            public void onResponse(Call<GetDataEquipmentAssigmentResponse> call, Response<GetDataEquipmentAssigmentResponse> response) {
                if (response.isSuccessful()){
                    GetDataEquipmentAssigmentResponse getDataEquipmentAssigmentResponse = response.body();
                    if (getDataEquipmentAssigmentResponse.getStatusID()==1){
                        datalist.removeAll(datalist);
                        datalist.addAll(getDataEquipmentAssigmentResponse.getData());
                        equitmentAdapter = new EquitmentAdapter(datalist,getApplication());
                        recy_equitment.setAdapter(equitmentAdapter);
                        recy_equitment.setLayoutManager(manager);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDataEquipmentAssigmentResponse> call, Throwable t) {
                Toast.makeText(getApplication(),""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class GobalEquitment {
        public String getFromdayfr() {
            return fromdayfr;
        }

        public void setFromdayfr(String fromdayfr) {
            this.fromdayfr = fromdayfr;
        }

        public String getTodayfr() {
            return todayfr;
        }

        public void setTodayfr(String todayfr) {
            this.todayfr = todayfr;
        }

        private String fromdayfr;
        private String todayfr;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        private String Status;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        private String UserName;


        public static List<DataAssigment> getDataAssigments() {
            return dataAssigments1;
        }

        public static void setDataAssigments(List<DataAssigment> dataAssigments) {
            GobalEquitment.dataAssigments1 = dataAssigments;
        }

        public static   List<DataAssigment> dataAssigments1 = new ArrayList<>() ;

    }
}
