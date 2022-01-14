package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.LeaveApplicationAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataAddleaveapplication;
import com.example.myapplication.Model.DataManager;
import com.example.myapplication.R;
import com.example.myapplication.Request.AddLeaveApplicationRequest;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Response.AddLeaveApplicationResponse;
import com.example.myapplication.Response.DataMangerResponse;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.LeaveApplicationResopnse;
import com.example.myapplication.Utils.GobalUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLeaveApplicationActivity extends AppCompatActivity {
    TextView todayaddleave;
    Button fromtimeaddleave;
    Button totimeaddleave;
    Button refuseaddleave, Acceptaddleave, SaveandSend;
    TextView fromdayaddleave;
    AppCompatSpinner TypeLeaveApplication;
    TextView leavereason;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    ArrayAdapter<DataManager> arrayAdapter;
    Spinner spinnerleave;
    String ManagerID;
    List<DataManager> dataManagers = new ArrayList<>();
    String leaveID = "00000000-0000-0000-0000-000000000000";
    SimpleDateFormat simpleDateFormat1;
    String IDSymbol = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        setContentView(R.layout.activity_addleaveapplication);
        DataMangerRequest dataMangerRequest = new DataMangerRequest();
        dataMangerRequest.setEmployeeID(Gobal.getUser().getUserID());
        GetDataManger(dataMangerRequest);
        GobalInfoLeave gobalInfoLeave = new GobalInfoLeave();
        fromdayaddleave = findViewById(R.id.fromdayaddleave);
        spinnerleave = findViewById(R.id.spinnerleave);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        simpleDateFormat1 = new SimpleDateFormat("MM/yyyy");
        int a = calendar.get(Calendar.DAY_OF_MONTH) + 1;
        gobalInfoLeave.setFromdayFr(simpleDateFormat.format(calendar.getTime()) + "/" + String.valueOf(a));
        fromdayaddleave.setText(String.valueOf(a) + "/" + simpleDateFormat1.format(calendar.getTime()));
        fromdayaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddLeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            fromdayaddleave.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromdayaddleave.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromdayaddleave.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromdayaddleave.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        todayaddleave = findViewById(R.id.todayaddleave);
        todayaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddLeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            todayaddleave.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            todayaddleave.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            todayaddleave.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            todayaddleave.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        leavereason = findViewById(R.id.leavereason);
        fromtimeaddleave = findViewById(R.id.fromtimeaddleave);
        fromtimeaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddLeaveApplicationActivity.this);
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
                        if (timePicker.getMinute() >= 0 && timePicker.getMinute() <= 9) {
                            fromtimeaddleave.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));

                        } else {
                            fromtimeaddleave.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        totimeaddleave = findViewById(R.id.totimeaddleave);
        totimeaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddLeaveApplicationActivity.this);
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
                        if (timePicker.getMinute() >= 0 && timePicker.getMinute() <= 9) {
                            totimeaddleave.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));
                        } else {
                            totimeaddleave.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        refuseaddleave = findViewById(R.id.refuseaddleave);
        refuseaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SaveandSend = findViewById(R.id.SaveandSend);
        SaveandSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAddleaveapplication dataAddleaveapplication = new DataAddleaveapplication();
                dataAddleaveapplication.setLeaveID(leaveID);
                dataAddleaveapplication.setUserId(Gobal.getUser().getUserID());
                dataAddleaveapplication.setLeaveReason(leavereason.getText().toString());
                dataAddleaveapplication.setSymbolID(IDSymbol);
                dataAddleaveapplication.setStatus(2);
                dataAddleaveapplication.setManagerID(ManagerID);
                dataAddleaveapplication.setLeaveApplicationType(1);
                dataAddleaveapplication.setFromDate(gobalInfoLeave.getFromdayFr() + "  " + fromtimeaddleave.getText().toString());
                dataAddleaveapplication.setToDate(gobalInfoLeave.getTodayFr() + "  " + totimeaddleave.getText().toString());
                if (dataAddleaveapplication.getLeaveID().equals("") == true || dataAddleaveapplication.getUserId().equals("") == true
                        || dataAddleaveapplication.getLeaveReason().equals("") == true
                        || dataAddleaveapplication.getFromDate().equals("") == true || dataAddleaveapplication.getToDate().equals("") == true) {
                    dialogcustom("Không Được Bỏ Trống");

                } else {
//                    showLoading();
                    AddLeaveApplicationRequest addLeaveApplicationRequest = new AddLeaveApplicationRequest();
                    addLeaveApplicationRequest.setLeaveApplication(dataAddleaveapplication);
                    GetDataAddLeaveAplication(addLeaveApplicationRequest);
//                    hideLoading();
                }
            }
        });
        TypeLeaveApplication = findViewById(R.id.TypeLeaveApplication);


        Acceptaddleave = findViewById(R.id.Acceptaddleave);
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);
        Acceptaddleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataAddleaveapplication dataAddleaveapplication = new DataAddleaveapplication();
                dataAddleaveapplication.setLeaveID("00000000-0000-0000-0000-000000000000");
                dataAddleaveapplication.setUserId(Gobal.getUser().getUserID());
                dataAddleaveapplication.setLeaveReason(leavereason.getText().toString());
                dataAddleaveapplication.setSymbolID(IDSymbol);
                dataAddleaveapplication.setStatus(1);
                dataAddleaveapplication.setManagerID(ManagerID);
                dataAddleaveapplication.setLeaveApplicationType(1);
                dataAddleaveapplication.setFromDate(gobalInfoLeave.getFromdayFr() + " " + fromtimeaddleave.getText().toString());
                dataAddleaveapplication.setToDate(gobalInfoLeave.getTodayFr() + " " + totimeaddleave.getText().toString());

                if (dataAddleaveapplication.getLeaveID().equals("") == true || dataAddleaveapplication.getUserId().equals("") == true
                        || dataAddleaveapplication.getLeaveReason().equals("") == true
                        || dataAddleaveapplication.getFromDate().equals("") == true || dataAddleaveapplication.getToDate().equals("") == true) {
                    dialogcustom("Không Được Bỏ Trống");

                } else {
//                      showLoading();
                    AddLeaveApplicationRequest addLeaveApplicationRequest = new AddLeaveApplicationRequest();
                    addLeaveApplicationRequest.setLeaveApplication(dataAddleaveapplication);
                    GetDataAddLeaveAplication(addLeaveApplicationRequest);
//                        hideLoading();
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
                            arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item, dataSymbols);
                            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            TypeLeaveApplication.setAdapter(arrayAdapter);
                            IDSymbol = getDataSymbolResponse.getData().get(0).getSymbolID();
                            TypeLeaveApplication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                        IDSymbol = getDataSymbolResponse.getData().get(position ).getSymbolID();
//                                        Toast.makeText(getApplication(), "" + IDSymbol, Toast.LENGTH_SHORT).show();


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

    public void GetDataAddLeaveAplication(AddLeaveApplicationRequest addLeaveApplicationRequest) {
        Call<AddLeaveApplicationResponse> addLeaveApplicationResponseCall = APIClient.DataAddLeaveApplication().GetDataLeaveApplication(Gobal.getGuiID(), addLeaveApplicationRequest);
        addLeaveApplicationResponseCall.enqueue(new Callback<AddLeaveApplicationResponse>() {
            @Override
            public void onResponse(Call<AddLeaveApplicationResponse> call, Response<AddLeaveApplicationResponse> response) {
                if (response.isSuccessful()) {
                    AddLeaveApplicationResponse addLeaveApplicationResponse = response.body();
                    if (addLeaveApplicationResponse.getStatusID() == 1) {
                        dialogcustom(addLeaveApplicationResponse.getErrorDescription());

                    } else if (addLeaveApplicationResponse.getStatusID() == -1) {
                        dialogcustom(addLeaveApplicationResponse.getErrorDescription());
                    }

                }
            }

            @Override
            public void onFailure(Call<AddLeaveApplicationResponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }

    public void GetDataManger(DataMangerRequest dataMangerRequest) {
        Call<DataMangerResponse> dataMangerResponseCall = APIClient.dataMangerSevice().GetManager(Gobal.getGuiID(), dataMangerRequest);
        dataMangerResponseCall.enqueue(new Callback<DataMangerResponse>() {
            @Override
            public void onResponse(Call<DataMangerResponse> call, Response<DataMangerResponse> response) {
                if (response.isSuccessful()) {
                    DataMangerResponse dataMangerResponse = response.body();
                    if (dataMangerResponse.getStatusID()==1) {
                        dataManagers.addAll(dataMangerResponse.getData());
                        arrayAdapter = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, dataManagers);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinnerleave.setAdapter(arrayAdapter);
                        ManagerID = dataMangerResponse.getData().get(0).getManagerID();
                        spinnerleave.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                ManagerID = dataMangerResponse.getData().get(position).getManagerID();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                } else {
                    dialogcustom("Lỗi Dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<DataMangerResponse> call, Throwable t) {
                dialogcustom("Không thể kết nối");
            }
        });
    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(AddLeaveApplicationActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imgexit = dialog.findViewById(R.id.exit);
        btnext = dialog.findViewById(R.id.accept);
        ttvTitle = dialog.findViewById(R.id.ttvTitle);
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

    public static class GobalInfoLeave {
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

}
