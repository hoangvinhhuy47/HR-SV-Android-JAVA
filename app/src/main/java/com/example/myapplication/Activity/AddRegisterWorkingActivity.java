package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataAddRegisterWorking;
import com.example.myapplication.Model.DataManager;
import com.example.myapplication.R;
import com.example.myapplication.Request.AddRegisterWorkingRequest;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Response.AddRegisterWorkingResponse;
import com.example.myapplication.Response.DataMangerResponse;
import com.example.myapplication.Response.GetDataSymbolResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRegisterWorkingActivity extends AppCompatActivity {
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    TextView fromdayregister,todayregister,reasonregister;
    Button fromtimeregister,totimeregister,saveregister,savesendregister,closeregiser;
    Spinner spinneraddworking;
    String ManagerID;
    List<DataManager> dataManagers = new ArrayList<>();
    ArrayAdapter<DataManager> arrayAdapter;
    AppCompatSpinner spinner_symboladdworking;
    ArrayAdapter arrayAdapter1;
    String IDSymbol="";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_addregisterworking);
        super.onCreate(savedInstanceState);
        DataMangerRequest dataMangerRequest = new DataMangerRequest();
        dataMangerRequest.setEmployeeID(Gobal.getUser().getUserID());
        GetDataManger(dataMangerRequest);
        GobaladdRegister gobaladdRegister = new GobaladdRegister();
        fromdayregister =findViewById(R.id.fromdayregister);
        todayregister=findViewById(R.id.todayregister);
        spinner_symboladdworking=findViewById(R.id.spinner_symboladdworking);
        reasonregister=findViewById(R.id.reasonregister);
        fromtimeregister=findViewById(R.id.fromtimeregister);
        totimeregister=findViewById(R.id.totimeregister);
        saveregister=findViewById(R.id.saveregister);
        savesendregister=findViewById(R.id.savesendregister);
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/yyyy");
        int a = calendar.get(Calendar.DAY_OF_MONTH)+1;
        fromdayregister.setText(String.valueOf(a)+"/"+simpleDateFormat1.format(calendar.getTime()));
        gobaladdRegister.setFromdayFr(simpleDateFormat.format(calendar.getTime())+"/"+String.valueOf(a));
        spinneraddworking = findViewById(R.id.spinneraddworking);
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);
        closeregiser=findViewById(R.id.closeregiser);
        closeregiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fromdayregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    int mYear = mcurrentDate.get(Calendar.YEAR);
                    int mMonth = mcurrentDate.get(Calendar.MONTH);
                    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker;
                    mDatePicker = new DatePickerDialog(AddRegisterWorkingActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            // TODO Auto-generated method stub
                            /*      Your code   to get date and time    */

                                selectedmonth = selectedmonth + 1;
                                if (selectedday < 9 && selectedmonth < 9) {
                                    fromdayregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                                    gobaladdRegister.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                                } else if (selectedday < 9 && selectedmonth > 9) {
                                    fromdayregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                                    gobaladdRegister.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                                } else if (selectedday > 9 && selectedmonth > 9) {
                                    fromdayregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                                    gobaladdRegister.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                                } else if (selectedday > 9 && selectedmonth < 9) {
                                    fromdayregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                                    gobaladdRegister.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                                }
                            }
                    }, mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select Date");
                    mDatePicker.show();
            }
        });
        todayregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddRegisterWorkingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            todayregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobaladdRegister.setTodayFr(""+selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9){
                            todayregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobaladdRegister.setTodayFr("" +selectedyear+ "/" + selectedmonth + "/" + "0" + selectedday );
                        }else if (selectedday > 9 && selectedmonth > 9) {
                            todayregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobaladdRegister.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            todayregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobaladdRegister.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        fromtimeregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AddRegisterWorkingActivity.this);
                dialog.setContentView(R.layout.dialogtime);
                TimePicker timePicker = dialog.findViewById(R.id.time123);
                Button btnoktime = dialog.findViewById(R.id.oktime);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btnclose = dialog.findViewById(R.id.closetime);
                timePicker.setIs24HourView(true);
                dialog.show();
                btnoktime.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        if (timePicker.getMinute()>=0 && timePicker.getMinute()<=9){
                            fromtimeregister.setText(String.valueOf(timePicker.getHour()) + ":" + "0" +String.valueOf(timePicker.getMinute()));

                        }
                        else {
                            fromtimeregister.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
                        }
                        dialog.dismiss();
                    }
                });
                btnclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        totimeregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AddRegisterWorkingActivity.this);
                dialog.setContentView(R.layout.dialogtime);
                TimePicker timePicker = dialog.findViewById(R.id.time123);
                Button btnoktime = dialog.findViewById(R.id.oktime);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btnclose = dialog.findViewById(R.id.closetime);
                timePicker.setIs24HourView(true);
                dialog.show();
                btnoktime.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        if (timePicker.getMinute()>=0 && timePicker.getMinute()<=9){
                            totimeregister.setText(String.valueOf(timePicker.getHour()) + ":" + "0" +String.valueOf(timePicker.getMinute()));

                        }
                        else {
                            totimeregister.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
                        }
                        dialog.dismiss();
                    }
                });
                btnclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        saveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAddRegisterWorking dataAddRegisterWorking = new DataAddRegisterWorking();
                dataAddRegisterWorking.setLeaveID("00000000-0000-0000-0000-000000000000");
                dataAddRegisterWorking.setUserId(Gobal.getUser().getUserID());
                dataAddRegisterWorking.setLeaveReason(reasonregister.getText().toString());
                dataAddRegisterWorking.setLeaveApplicationType(2);
                dataAddRegisterWorking.setStatus(1);
                dataAddRegisterWorking.setSymbolID(IDSymbol);
                dataAddRegisterWorking.setManagerID(ManagerID);
                dataAddRegisterWorking.setFromDate(gobaladdRegister.getFromdayFr()+" "+fromtimeregister.getText().toString());
                dataAddRegisterWorking.setToDate(gobaladdRegister.getTodayFr()+"  "+totimeregister.getText().toString());
                if (dataAddRegisterWorking.getLeaveID().equals("")==true || dataAddRegisterWorking.getUserId().equals("")==true
                        || dataAddRegisterWorking.getLeaveReason().equals("")==true ||dataAddRegisterWorking.getLeaveApplicationType()==0
                        || dataAddRegisterWorking.getFromDate().equals("")==true || dataAddRegisterWorking.getToDate().equals("")==true) {
                    dialogcustom("Không Được Bỏ Trống");
                }
                else {
                    AddRegisterWorkingRequest addRegisterWorkingRequest = new AddRegisterWorkingRequest();
                    addRegisterWorkingRequest.setLeaveApplication(dataAddRegisterWorking);
                    GetRegister(addRegisterWorkingRequest);
                }
            }
        });
        savesendregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAddRegisterWorking dataAddRegisterWorking = new DataAddRegisterWorking();
                dataAddRegisterWorking.setLeaveID("00000000-0000-0000-0000-000000000000");
                dataAddRegisterWorking.setUserId(Gobal.getUser().getUserID());
                dataAddRegisterWorking.setLeaveReason(reasonregister.getText().toString());
                dataAddRegisterWorking.setLeaveApplicationType(2);
                dataAddRegisterWorking.setStatus(2);
                dataAddRegisterWorking.setSymbolID(IDSymbol);
                dataAddRegisterWorking.setFromDate(gobaladdRegister.getFromdayFr()+" "+fromtimeregister.getText().toString());
                dataAddRegisterWorking.setToDate(gobaladdRegister.getTodayFr()+"  "+totimeregister.getText().toString());
                dataAddRegisterWorking.setManagerID(ManagerID);
                if (dataAddRegisterWorking.getLeaveID().equals("")==true || dataAddRegisterWorking.getUserId().equals("")==true
                        || dataAddRegisterWorking.getLeaveReason().equals("")==true ||dataAddRegisterWorking.getLeaveApplicationType()==0
                        || dataAddRegisterWorking.getFromDate().equals("")==true || dataAddRegisterWorking.getToDate().equals("")==true) {
                    dialogcustom("Không Được Bỏ Trống");
                }
                else {
                    AddRegisterWorkingRequest addRegisterWorkingRequest = new AddRegisterWorkingRequest();
                    addRegisterWorkingRequest.setLeaveApplication(dataAddRegisterWorking);
                    GetRegister(addRegisterWorkingRequest);
                }
            }
        });

    }
    public void GetDataSymbol(GetDataSymbolRequest getDataSymbolRequest) {
        Call<GetDataSymbolResponse> getDataSymbolResponseCall = APIClient.getDataSymbolSerVice().GetDataSymbol(Gobal.getGuiID(), getDataSymbolRequest);
        getDataSymbolResponseCall.enqueue(new Callback<GetDataSymbolResponse>() {
            @Override
            public void onResponse(Call<GetDataSymbolResponse> call, Response<GetDataSymbolResponse> response) {
                if (response.isSuccessful()) {
                    GetDataSymbolResponse getDataSymbolResponse = response.body();
                    if (getDataSymbolResponse.getStatusID() == 1) {
                        try {
                            List<String> dataSymbols = new ArrayList<>();
                            for (int i = 0; i < getDataSymbolResponse.getData().size(); i++) {
                                dataSymbols.add(getDataSymbolResponse.getData().get(i).getSymbolName());
                            }
                            arrayAdapter1 = new ArrayAdapter(getApplication(), R.layout.spinner_item, dataSymbols);
                            arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            spinner_symboladdworking.setAdapter(arrayAdapter1);
                            IDSymbol = getDataSymbolResponse.getData().get(0).getSymbolID();
                            spinner_symboladdworking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    IDSymbol = getDataSymbolResponse.getData().get(position ).getSymbolID();
                                    Toast.makeText(getApplication(), "" + IDSymbol, Toast.LENGTH_SHORT).show();


                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } catch (Exception exception) {
                            Toast.makeText(getApplication(), "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDataSymbolResponse> call, Throwable t) {

            }
        });
    }
    public void GetRegister(AddRegisterWorkingRequest addRegisterWorkingRequest){
        Call<AddRegisterWorkingResponse> registerWorkingResponseCall = APIClient.DataRegisterWorking().GetRegisterWorking(Gobal.getGuiID(), addRegisterWorkingRequest);
        registerWorkingResponseCall.enqueue(new Callback<AddRegisterWorkingResponse>() {
            @Override
            public void onResponse(Call<AddRegisterWorkingResponse> call, Response<AddRegisterWorkingResponse> response) {
                if (response.isSuccessful()){
                    AddRegisterWorkingResponse addRegisterWorkingResponse = response.body();
                    if (addRegisterWorkingResponse.getStatusID()==1){
                        dialogcustom("Thành Công");

                    }
                    else
                        dialogcustom(addRegisterWorkingResponse.getErrorDescription());

                }
            }
            @Override
            public void onFailure(Call<AddRegisterWorkingResponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }
    public void dialogcustom(String text){
        final Dialog dialog = new Dialog(AddRegisterWorkingActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgexit = dialog.findViewById(R.id.exit);
        btnext = dialog.findViewById(R.id.accept);
        ttvTitle=dialog.findViewById(R.id.ttvTitle);
        ttvTitle.setText(text);
        dialog.show();
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    public static class GobaladdRegister{
        private String FromdayFr;

        public String getFromdayFr() {
            return FromdayFr;
        }

        public void setFromdayFr(String fromdayFr) {
            FromdayFr = fromdayFr;
        }

        public String getTodayFr() {
            return TodayFr;
        }

        public void setTodayFr(String todayFr) {
            TodayFr = todayFr;
        }

        private String TodayFr;
    }
    public void GetDataManger(DataMangerRequest dataMangerRequest){
        Call<DataMangerResponse> dataMangerResponseCall = APIClient.dataMangerSevice().GetManager(Gobal.getGuiID(),dataMangerRequest);
        dataMangerResponseCall.enqueue(new Callback<DataMangerResponse>() {
            @Override
            public void onResponse(Call<DataMangerResponse> call, Response<DataMangerResponse> response) {
                if (response.isSuccessful()){
                    DataMangerResponse dataMangerResponse = response.body();
                    dataManagers.addAll(dataMangerResponse.getData());
                    arrayAdapter = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, dataManagers);
                    arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinneraddworking.setAdapter(arrayAdapter);
                    ManagerID = dataMangerResponse.getData().get(0).getManagerID();
                    spinneraddworking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                            ManagerID = dataMangerResponse.getData().get(position).getManagerID();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else {
                    dialogcustom("Lỗi Dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<DataMangerResponse> call, Throwable t) {
                dialogcustom("Không thể kết nối");
            }
        });
    }
}
