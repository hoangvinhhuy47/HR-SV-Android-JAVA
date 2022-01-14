package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.myapplication.Model.DataDeleteRegisterWorking;
import com.example.myapplication.Model.DataManager;
import com.example.myapplication.Model.DataUpdateRegiseterWorking;
import com.example.myapplication.R;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Request.DeleteRegisterWorikingRequest;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.UpdateRegisterWorkingRequest;
import com.example.myapplication.Response.DataMangerResponse;
import com.example.myapplication.Response.DeleteRegisterWorikingResponse;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.UpdateRegisterWorkingRespone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationRegisterWorking extends AppCompatActivity {
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    TextView fromdayinfregister, todayinfregister, reasoninfregister, reasonrefuseworkingtext, reasonrefuseworking;
    Button fromtimeinfregister, totimeinfregister, saveinfregister, savesendinfregister, closeinfregiser, deleteregister;
    Spinner spinnerworking;
    List<DataManager> dataManagers = new ArrayList<>();
    ArrayAdapter<DataManager> arrayAdapter1;
    String ManagerID;
    Spinner spinner_symbolworking;
    ArrayAdapter arrayAdapter;
    String IDSymbol = "";
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_infomationworking);
        super.onCreate(savedInstanceState);
        intent = this.getIntent();
        GobalInfoRegister gobalInfoRegister = new GobalInfoRegister();
        ManagerID = intent.getStringExtra("ManagerID");
        spinner_symbolworking = findViewById(R.id.spinner_symbolworking);
        DataMangerRequest dataMangerRequest = new DataMangerRequest();
        dataMangerRequest.setEmployeeID(Gobal.getUser().getUserID());
        GetDataManger(dataMangerRequest);
        reasonrefuseworkingtext = findViewById(R.id.reasonrefuseworkingtext);
        reasonrefuseworking = findViewById(R.id.reasonrefuseworking);
        fromdayinfregister = findViewById(R.id.fromdayinfregister);
        todayinfregister = findViewById(R.id.todayinfregister);
        reasoninfregister = findViewById(R.id.reasoninfregister);
        fromtimeinfregister = findViewById(R.id.fromtimeinfregister);
        totimeinfregister = findViewById(R.id.totimeinfregister);
        saveinfregister = findViewById(R.id.saveinfregister);
        savesendinfregister = findViewById(R.id.savesendinfregister);
        closeinfregiser = findViewById(R.id.closeinfregiser);
        deleteregister = findViewById(R.id.deleteregister);
        /////
        spinnerworking = findViewById(R.id.spinnerworking);

        fromdayinfregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(InformationRegisterWorking.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            fromdayinfregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromdayinfregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromdayinfregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromdayinfregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        todayinfregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(InformationRegisterWorking.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            todayinfregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            todayinfregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            todayinfregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            todayinfregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoRegister.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        fromtimeinfregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InformationRegisterWorking.this);
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
                            fromtimeinfregister.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));

                        } else {
                            fromtimeinfregister.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        totimeinfregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InformationRegisterWorking.this);
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
                            totimeinfregister.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));

                        } else {
                            totimeinfregister.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        fromdayinfregister.setText(intent.getStringExtra("FromdayRegister1"));
        todayinfregister.setText(intent.getStringExtra("TodayRegister1"));
        fromtimeinfregister.setText(intent.getStringExtra("FromTimeRegister1"));
        totimeinfregister.setText(intent.getStringExtra("ToTimeRegister1"));
        reasoninfregister.setText(intent.getStringExtra("ReasonRegister"));
        gobalInfoRegister.setFromdayFr(intent.getStringExtra("FromdayRegister"));
        gobalInfoRegister.setTodayFr(intent.getStringExtra("TodayRegister"));
        closeinfregiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);
        if (intent.getStringExtra("StatusRegister").equals("1") == false) {
            savesendinfregister.setVisibility(View.VISIBLE);
            saveinfregister.setVisibility(View.VISIBLE);
            reasonrefuseworkingtext.setVisibility(View.VISIBLE);
            reasonrefuseworking.setVisibility(View.VISIBLE);
            reasonrefuseworking.setText(intent.getStringExtra("ActionNotesWorking"));
            deleteregister.setVisibility(View.VISIBLE);
        } else {
            reasonrefuseworkingtext.setVisibility(View.INVISIBLE);
            reasonrefuseworking.setVisibility(View.INVISIBLE);
            savesendinfregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataUpdateRegiseterWorking Data = new DataUpdateRegiseterWorking();
                    Data.setLeaveID(intent.getStringExtra("LeaveIDInfo"));
                    Data.setFromDate(gobalInfoRegister.getFromdayFr() + " " + fromtimeinfregister.getText().toString());
                    Data.setToDate(gobalInfoRegister.getTodayFr() + " " + totimeinfregister.getText().toString());
                    Data.setLeaveReason(reasoninfregister.getText().toString());
                    Data.setStatus(2);
                    Data.setManagerID(ManagerID);
                    Data.setLeaveApplicationType(2);
                    Data.setUserId(Gobal.getUser().getUserID());
                    UpdateRegisterWorkingRequest updateRegisterWorkingRequest = new UpdateRegisterWorkingRequest();
                    updateRegisterWorkingRequest.setLeaveApplication(Data);
                    GetInformationWorking(updateRegisterWorkingRequest);
                }
            });
            saveinfregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataUpdateRegiseterWorking Data = new DataUpdateRegiseterWorking();
                    Data.setLeaveID(intent.getStringExtra("LeaveIDInfo"));
                    Data.setFromDate(gobalInfoRegister.getFromdayFr() + " " + fromtimeinfregister.getText().toString());
                    Data.setToDate(gobalInfoRegister.getTodayFr() + " " + totimeinfregister.getText().toString());
                    Data.setLeaveReason(reasoninfregister.getText().toString());
                    Data.setManagerID(ManagerID);
                    Data.setStatus(1);
                    Data.setLeaveApplicationType(2);
                    Data.setUserId(Gobal.getUser().getUserID());
                    UpdateRegisterWorkingRequest updateRegisterWorkingRequest = new UpdateRegisterWorkingRequest();
                    updateRegisterWorkingRequest.setLeaveApplication(Data);
                    GetInformationWorking(updateRegisterWorkingRequest);
                }
            });
            deleteregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(InformationRegisterWorking.this);
                    dialog.setContentView(R.layout.dialog_checkintime);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ImageView imgexitdialog = dialog.findViewById(R.id.exit);
                    TextView ttvTitleCheckIn = dialog.findViewById(R.id.ttvTitleCheckIn);
                    Button acceptCheckIn = dialog.findViewById(R.id.acceptCheckIn);
                    Button closeCheckIn = dialog.findViewById(R.id.closeCheckIn);
                    dialog.show();
                    ttvTitleCheckIn.setText("Bạn Có Muốn Thực Hiện Hay Không?");
                    imgexitdialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    closeCheckIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    acceptCheckIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataDeleteRegisterWorking dataDeleteRegisterWorking = new DataDeleteRegisterWorking();
                            dataDeleteRegisterWorking.setLeaveID(intent.getStringExtra("LeaveIDInfo"));
                            DeleteRegisterWorikingRequest deleteRegisterWorikingRequest = new DeleteRegisterWorikingRequest();
                            deleteRegisterWorikingRequest.setLeaveApplication(dataDeleteRegisterWorking);
                            DeleteRegisterWorking(deleteRegisterWorikingRequest);
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }

    public void DeleteRegisterWorking(DeleteRegisterWorikingRequest deleteRegisterWorikingRequest) {
        Call<DeleteRegisterWorikingResponse> deleteLeaveApplicationResponseCall = APIClient.DeleRegister().GetDeleRegister(Gobal.getGuiID(), deleteRegisterWorikingRequest);
        deleteLeaveApplicationResponseCall.enqueue(new Callback<DeleteRegisterWorikingResponse>() {
            @Override
            public void onResponse(Call<DeleteRegisterWorikingResponse> call, Response<DeleteRegisterWorikingResponse> response) {
                if (response.isSuccessful()) {
                    DeleteRegisterWorikingResponse deleteRegisterWorikingResponse = response.body();
                    if (deleteRegisterWorikingResponse.getStatusID() == 1) {

                        dialogcustom("Xoá Thành Công");
                        finish();


                    } else
                        dialogcustom(deleteRegisterWorikingResponse.getErrorDescription());
                }
            }

            @Override
            public void onFailure(Call<DeleteRegisterWorikingResponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });
    }

    public void GetInformationWorking(UpdateRegisterWorkingRequest updateRegisterWorkingRequest) {
        Call<UpdateRegisterWorkingRespone> updateRegisterWorkingResponeCall = APIClient.InfomationRegister().GetUpdateRegister(Gobal.getGuiID(), updateRegisterWorkingRequest);
        updateRegisterWorkingResponeCall.enqueue(new Callback<UpdateRegisterWorkingRespone>() {
            @Override
            public void onResponse(Call<UpdateRegisterWorkingRespone> call, Response<UpdateRegisterWorkingRespone> response) {
                if (response.isSuccessful()) {
                    UpdateRegisterWorkingRespone updateRegisterWorkingRespone = response.body();
                    if (updateRegisterWorkingRespone.getStatusID() == 1) {
                        dialogcustom("Cập Nhật Thành Công");

                    } else if (updateRegisterWorkingRespone.getStatusID() == -1) {
                        dialogcustom(updateRegisterWorkingRespone.ErrorDescription);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateRegisterWorkingRespone> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(InformationRegisterWorking.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.alert_dialog);
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

    public static class GobalInfoRegister {
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

        public String getManagerID() {
            return ManagerID;
        }

        public void setManagerID(String managerID) {
            ManagerID = managerID;
        }

        private String ManagerID;
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
                            dataSymbols.add("Tất Cả");
                            for (int i = 0; i < getDataSymbolResponse.getData().size(); i++) {
                                dataSymbols.add(getDataSymbolResponse.getData().get(i).getSymbolName());
                            }
                            arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item, dataSymbols);
                            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            spinner_symbolworking.setAdapter(arrayAdapter);
                            for (int i = 0; i < getDataSymbolResponse.getData().size(); i++) {
                                if (intent.getStringExtra("IDSymbol") != null) {
                                    if (getDataSymbolResponse.getData().get(i).getSymbolID().equals(intent.getStringExtra("IDSymbol")) == true) {
                                        spinner_symbolworking.setSelection(i + 1, true);
                                        getDataSymbolResponse.getData().get(i).getSymbolID();
                                        IDSymbol = intent.getStringExtra("IDSymbol");
                                        break;
                                    }
                                } else {
                                    spinner_symbolworking.setSelection(0, true);
                                    IDSymbol = "";
                                    break;
                                }
                            }
                            spinner_symbolworking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0) {
                                        IDSymbol = getDataSymbolResponse.getData().get(position - 1).getSymbolID();
                                        Toast.makeText(getApplication(), "" + IDSymbol, Toast.LENGTH_SHORT).show();
                                    } else {
                                        IDSymbol = "";
                                    }
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
                dialogcustom("Không thể kết nối");
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
                    dataManagers.addAll(dataMangerResponse.getData());
                    arrayAdapter1 = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, dataManagers);
                    arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinnerworking.setAdapter(arrayAdapter1);
                    if (ManagerID != null) {
                        try {
                            for (int i = 0; i <= dataManagers.size(); i++) {
                                if (dataManagers.get(i).getManagerID().equals(ManagerID)) {
                                    spinnerworking.setSelection(i);
                                    break;
                                }

                            }
                        } catch (Exception exception) {
                            Toast.makeText(getApplication(), "" + exception, Toast.LENGTH_SHORT).show();
                        }

                    }
                    spinnerworking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                            ManagerID = dataMangerResponse.getData().get(position).getManagerID();
                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


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
}
