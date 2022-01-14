package com.example.myapplication.Activity;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
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
import com.example.myapplication.Model.DataDeleteLeaveApplication;
import com.example.myapplication.Model.DataManager;
import com.example.myapplication.Model.DataUpdateLeaveApplication;
import com.example.myapplication.R;
import com.example.myapplication.Request.CheckinRequest;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Request.DeleteLeaveApplicationRequest;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Request.UpdateLeaveApplicationRequest;
import com.example.myapplication.Response.DataMangerResponse;
import com.example.myapplication.Response.DeleteLeaveApplicationResponse;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.UpdateLeaveApplicationReponse;
import com.example.myapplication.Utils.GobalUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfomationLeaveAplication extends AppCompatActivity {

    TextView  fromdayleaveinf, todayleaveinf, reasonleaveinf, reasonrefusetext, reasonrefuse;
    AppCompatSpinner typeleaveinf;
    Button fromtimeleaveinf, totimeleaveinf, savedialog, savesenddialog, closedialog, deleteleave;
    ImageView imgexit;
    RelativeLayout group123;
    Button btnext;
    TextView ttvTitle;
    Spinner spinnerleaveinf;
    List<DataManager> dataManagers = new ArrayList<>();
    ArrayAdapter<DataManager> arrayAdapter;
    String ManagerID;
    String IDSymbol="";
    Intent intent;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_infomationleave);
        intent = this.getIntent();
        super.onCreate(savedInstanceState);

        ManagerID = intent.getStringExtra("ManagerID");
        DataMangerRequest dataMangerRequest = new DataMangerRequest();
        dataMangerRequest.setEmployeeID(Gobal.getUser().getUserID());
        GetDataManger(dataMangerRequest);
        spinnerleaveinf = findViewById(R.id.spinnerleaveinf);
        reasonrefusetext = findViewById(R.id.reasonrefusetext);
        group123 = findViewById(R.id.group123);
        reasonrefuse = findViewById(R.id.reasonrefuse);
        spinnerleaveinf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                DataManager manager = dataManagers.get(position);
                ManagerID = manager.getManagerID();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        GobalInfoLeave gobalInfoLeave = new GobalInfoLeave();
        typeleaveinf = findViewById(R.id.typeleaveinf);
        fromdayleaveinf = findViewById(R.id.fromdayleaveinf);

        String b = intent.getStringExtra("FromDay");
        gobalInfoLeave.setFromdayfr(intent.getStringExtra("FromDay1"));
        fromdayleaveinf.setText(b);
        deleteleave = findViewById(R.id.deleteleave);

        fromdayleaveinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(InfomationLeaveAplication.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            fromdayleaveinf.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromdayleaveinf.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromdayleaveinf.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromdayleaveinf.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        todayleaveinf = findViewById(R.id.todayleaveinf);
        reasonleaveinf = findViewById(R.id.reasonleaveinf);
        String f = intent.getStringExtra("LeaveReson");
        reasonleaveinf.setText(f);

        fromtimeleaveinf = findViewById(R.id.fromtimeleaveinf);
        String c = intent.getStringExtra("FromTime");
        fromtimeleaveinf.setText(c);
        fromtimeleaveinf.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InfomationLeaveAplication.this);
                dialog.setContentView(R.layout.dialogtime);
                TimePicker timePicker = dialog.findViewById(R.id.time123);
                Button btnoktime = dialog.findViewById(R.id.oktime);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btnclose = dialog.findViewById(R.id.closetime);
                timePicker.setIs24HourView(true);
                dialog.show();
                btnoktime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (timePicker.getMinute() >= 0 && timePicker.getMinute() <= 9) {
                            fromtimeleaveinf.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));

                        } else {
                            fromtimeleaveinf.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        totimeleaveinf = findViewById(R.id.totimeleaveinf);
        String d = intent.getStringExtra("ToDay");
        gobalInfoLeave.setTodayfr(intent.getStringExtra("ToDay1"));
        todayleaveinf.setText(d);
        todayleaveinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(InfomationLeaveAplication.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            todayleaveinf.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            todayleaveinf.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            todayleaveinf.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            todayleaveinf.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalInfoLeave.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        String e = intent.getStringExtra("ToTime");
        totimeleaveinf.setText(e);
        totimeleaveinf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(InfomationLeaveAplication.this);
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
                            totimeleaveinf.setText(String.valueOf(timePicker.getHour()) + ":" + "0" + String.valueOf(timePicker.getMinute()));

                        } else {
                            totimeleaveinf.setText(String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute()));
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
        savedialog = findViewById(R.id.savedialog);
        savesenddialog = findViewById(R.id.savesenddialog);
        if (intent.getStringExtra("StatusLeaveApplication").equals("1") == false) {
            savedialog.setVisibility(View.VISIBLE);
            reasonrefusetext.setVisibility(View.VISIBLE);
            reasonrefuse.setVisibility(View.VISIBLE);
            group123.setVisibility(View.VISIBLE);
            reasonrefuse.setText(intent.getStringExtra("ActionNotes"));
            savesenddialog.setVisibility(View.VISIBLE);
            deleteleave.setVisibility(View.VISIBLE);
            savedialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(),"Bạn không thể thay đổi trang thái.",Toast.LENGTH_SHORT).show();
                }
            });
            savesenddialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(),"Bạn không thể thay đổi trang thái.",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            group123.setVisibility(View.GONE);
//            reasonrefusetext.setVisibility(View.GONE);
            reasonrefusetext.setVisibility(View.INVISIBLE);
            reasonrefuse.setVisibility(View.INVISIBLE);
//            reasonrefusetext.setVisibility(View.GONE);
//            reasonrefuse.setText(intent.getStringExtra("ActionNotes"));

            savedialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataUpdateLeaveApplication updateLeaveApplicationRequest = new DataUpdateLeaveApplication();
                    updateLeaveApplicationRequest.setLeaveID(intent.getStringExtra("LeaveID"));
                    updateLeaveApplicationRequest.setUserID(Gobal.getUser().getUserID());
                    updateLeaveApplicationRequest.setLeaveReason(reasonleaveinf.getText().toString());
                    updateLeaveApplicationRequest.setSymbolID(IDSymbol);
                    updateLeaveApplicationRequest.setStatus(1);
                    updateLeaveApplicationRequest.setManagerID(ManagerID);
                    updateLeaveApplicationRequest.setLeaveApplicationType(1);
                    updateLeaveApplicationRequest.setFromDate(gobalInfoLeave.getFromdayfr() + " " + fromtimeleaveinf.getText().toString());
                    updateLeaveApplicationRequest.setToDate(gobalInfoLeave.getTodayfr() + " " + totimeleaveinf.getText().toString());
                    if (updateLeaveApplicationRequest.getLeaveReason().equals("") == true || updateLeaveApplicationRequest.getFromDate().equals("") == true
                            || updateLeaveApplicationRequest.getToDate().equals("") == true) {
                        dialogcustom("Không được để trống");
                    } else {
                        UpdateLeaveApplicationRequest updateLeaveApplicationRequest1 = new UpdateLeaveApplicationRequest();
                        updateLeaveApplicationRequest1.setLeaveApplication(updateLeaveApplicationRequest);
                        GetUpdateLeave(updateLeaveApplicationRequest1);
                    }
                }
            });

            savesenddialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataUpdateLeaveApplication updateLeaveApplicationRequest = new DataUpdateLeaveApplication();
                    updateLeaveApplicationRequest.setLeaveID(intent.getStringExtra("LeaveID"));
                    updateLeaveApplicationRequest.setUserID(Gobal.getUser().getUserID());
                    updateLeaveApplicationRequest.setLeaveReason(reasonleaveinf.getText().toString());
                    updateLeaveApplicationRequest.setSymbolID(IDSymbol);
                    updateLeaveApplicationRequest.setStatus(2);
                    updateLeaveApplicationRequest.setFromDate(gobalInfoLeave.getFromdayfr() + " " + fromtimeleaveinf.getText().toString());
                    updateLeaveApplicationRequest.setToDate(gobalInfoLeave.getTodayfr() + " " + totimeleaveinf.getText().toString());
                    updateLeaveApplicationRequest.setManagerID(ManagerID);
                    updateLeaveApplicationRequest.setLeaveApplicationType(1);
                    if (updateLeaveApplicationRequest.getLeaveReason().equals("") == true || updateLeaveApplicationRequest.getFromDate().equals("") == true
                            || updateLeaveApplicationRequest.getToDate().equals("") == true) {
                        dialogcustom("Không được để trống");
                    } else {
                        UpdateLeaveApplicationRequest updateLeaveApplicationRequest1 = new UpdateLeaveApplicationRequest();
                        updateLeaveApplicationRequest1.setLeaveApplication(updateLeaveApplicationRequest);
                        GetUpdateLeave(updateLeaveApplicationRequest1);
                    }
                }
            });

            deleteleave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(InfomationLeaveAplication.this);
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
                            DataDeleteLeaveApplication dataDeleteLeaveApplication = new DataDeleteLeaveApplication();
                            dataDeleteLeaveApplication.setLeaveID(intent.getStringExtra("LeaveID"));
                            DeleteLeaveApplicationRequest deleteLeaveApplicationRequest = new DeleteLeaveApplicationRequest();
                            deleteLeaveApplicationRequest.setLeaveApplication(dataDeleteLeaveApplication);
                            GetDeleLeaveApplication(deleteLeaveApplicationRequest);
                            dialog.dismiss();

                        }
                    });

                }
            });
        }

        closedialog = findViewById(R.id.closedialog);
        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           finish();
        }
        });
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);
    }
    public void GetDataSymbol(GetDataSymbolRequest getDataSymbolRequest){
        Call<GetDataSymbolResponse> getDataSymbolResponseCall =APIClient.getDataSymbolSerVice().GetDataSymbol(Gobal.getGuiID(),getDataSymbolRequest);
        getDataSymbolResponseCall.enqueue(new Callback<GetDataSymbolResponse>() {
            @Override
            public void onResponse(Call<GetDataSymbolResponse> call, Response<GetDataSymbolResponse> response) {
                if (response.isSuccessful()){
                    GetDataSymbolResponse getDataSymbolResponse = response.body();
                    if (getDataSymbolResponse.getStatusID()==1) {
                        try {
                            List<String> dataSymbols = new ArrayList<>();

                            for (int i = 0; i<getDataSymbolResponse.getData().size();i++){
                                dataSymbols.add(getDataSymbolResponse.getData().get(i).getSymbolName());
                            }
                            arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item, dataSymbols);
                            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            typeleaveinf.setAdapter(arrayAdapter);
                            for (int i = 0;i<getDataSymbolResponse.getData().size();i++){
                                if (intent.getStringExtra("IDSymbol")!=null) {
                                    if (getDataSymbolResponse.getData().get(i).getSymbolID().equals(intent.getStringExtra("IDSymbol"))==true){
                                        typeleaveinf.setSelection(i,true);
                                        getDataSymbolResponse.getData().get(i).getSymbolID();
                                        IDSymbol = intent.getStringExtra("IDSymbol");
                                        break;
                                    }
                                }else {
                                    typeleaveinf.setSelection(0,true);
                                    IDSymbol ="";
                                    break;
                                }
                            }
                            typeleaveinf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position != 0){
                                        IDSymbol = getDataSymbolResponse.getData().get(position-1).getSymbolID();
                                        Toast.makeText(getApplication(),""+IDSymbol,Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        IDSymbol="";
                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }
                        catch (Exception exception){
                            Toast.makeText(getApplication(),""+exception.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDataSymbolResponse> call, Throwable t) {

            }
        });
    }
    public void GetUpdateLeave(UpdateLeaveApplicationRequest updateLeaveApplicationRequest) {
        Call<UpdateLeaveApplicationReponse> updateLeaveApplicationReponseCall = APIClient.DataUpdateleave().GetDataUpdateLeave(Gobal.getGuiID(), updateLeaveApplicationRequest);
        updateLeaveApplicationReponseCall.enqueue(new Callback<UpdateLeaveApplicationReponse>() {
            @Override
            public void onResponse(Call<UpdateLeaveApplicationReponse> call, Response<UpdateLeaveApplicationReponse> response) {
                if (response.isSuccessful()) {
                    UpdateLeaveApplicationReponse updateLeaveApplicationReponse = response.body();
                    if (updateLeaveApplicationReponse.getStatusID() == 1) {
                        dialogcustom("Thành Công");

                    } else
                        dialogcustom(updateLeaveApplicationReponse.getErrorDescription());

                }
            }

            @Override
            public void onFailure(Call<UpdateLeaveApplicationReponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }

    public void GetDeleLeaveApplication(DeleteLeaveApplicationRequest deleteLeaveApplicationRequest) {
        Call<DeleteLeaveApplicationResponse> deleteLeaveApplicationResponseCall = APIClient.DeleLeaveApplication().GetDeleLeave(Gobal.getGuiID(), deleteLeaveApplicationRequest);
        deleteLeaveApplicationResponseCall.enqueue(new Callback<DeleteLeaveApplicationResponse>() {
            @Override
            public void onResponse(Call<DeleteLeaveApplicationResponse> call, Response<DeleteLeaveApplicationResponse> response) {
                if (response.isSuccessful()) {
                    DeleteLeaveApplicationResponse deleteLeaveApplicationResponse = response.body();
                    if (deleteLeaveApplicationResponse.getStatusID() == 1) {
                        dialogcustom("Xóa Thành Công");
                        finish();

                    } else
                        dialogcustom(deleteLeaveApplicationResponse.getErrorDescription());

                }
            }

            @Override
            public void onFailure(Call<DeleteLeaveApplicationResponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(InfomationLeaveAplication.this);
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


    public class GobalInfoLeave {
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
    }

    public void GetDataManger(DataMangerRequest dataMangerRequest) {
        Call<DataMangerResponse> dataMangerResponseCall = APIClient.dataMangerSevice().GetManager(Gobal.getGuiID(), dataMangerRequest);
        dataMangerResponseCall.enqueue(new Callback<DataMangerResponse>() {
            @Override
            public void onResponse(Call<DataMangerResponse> call, Response<DataMangerResponse> response) {
                if (response.isSuccessful()) {
                    DataMangerResponse dataMangerResponse = response.body();
                    dataManagers.addAll(dataMangerResponse.getData());
                    arrayAdapter = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, dataManagers);
                    arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinnerleaveinf.setAdapter(arrayAdapter);
                    if (ManagerID != null) {
                        try {
                            for (int i = 0; i <= dataManagers.size(); i++) {
                                if (dataManagers.get(i).getManagerID().equals(ManagerID)) {
                                    spinnerleaveinf.setSelection(i);
                                    ManagerID=dataManagers.get(i).getManagerID();
                                    break;
                                }

                            }
                        }catch (Exception exception){
                            Toast.makeText(getApplication(), "Lỗi! Xin vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }

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
}
