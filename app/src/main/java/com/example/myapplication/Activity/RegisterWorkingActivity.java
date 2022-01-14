package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.RegisterWorkingAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataRegisterWorking;
import com.example.myapplication.R;
import com.example.myapplication.Request.DataMangerRequest;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Request.RegisterWorkingRequest;
import com.example.myapplication.Response.DataMangerResponse;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.RegisterWorkingReponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterWorkingActivity extends AppCompatActivity {
    TextView fromdaydataregister, todaydataregister;
    Button btnsearchregister, addregisterworking;
    ImageView imgexit, backregister;
    Button btnext;
    TextView ttvTitle;
    RegisterWorkingAdapter adapter;
    RecyclerView rycDataregister;
    LinearLayoutManager linearLayoutManager;
    Boolean isScrolling = false;
    int pagenumber = 1;
    String fromdayit, todayit;
    AppCompatSpinner symbolid;
    String IDSymbol = "";
    List<DataRegisterWorking> Datalist = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    GobalRegister gobalRegister;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_dataregisterworking);
        super.onCreate(savedInstanceState);
        gobalRegister = new GobalRegister();
        fromdaydataregister = findViewById(R.id.fromdaydataregister);
        todaydataregister = findViewById(R.id.todaydataregister);
        btnsearchregister = findViewById(R.id.btnsearchregister);
        addregisterworking = findViewById(R.id.addregisterworking);
        symbolid = findViewById(R.id.symbolid);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat abc1 = new SimpleDateFormat("yyyy/MM/dd");
        String a = simpleDateFormat.format(calendar.getTime());
        gobalRegister.setTodayfr(abc1.format(calendar.getTime()));
        todaydataregister.setText(a);
        SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
        SimpleDateFormat abc2 = new SimpleDateFormat("yyyy/MM/01");
        String b = abc.format(calendar.getTime());
        gobalRegister.setFromdayfr(abc2.format(calendar.getTime()));
        fromdaydataregister.setText(b);
        RegisterWorkingRequest registerWorkingRequest = new RegisterWorkingRequest();
        registerWorkingRequest.setEmployeeID(Gobal.getUser().getUserID());
        registerWorkingRequest.setFromDate(abc2.format(calendar.getTime()));
        registerWorkingRequest.setToDate(abc1.format(calendar.getTime()));
        registerWorkingRequest.setPageNumber(pagenumber);
        registerWorkingRequest.setSymbolID(IDSymbol);
        GetDataRegister(registerWorkingRequest);
//        DataMangerRequest dataMangerRequest = new DataMangerRequest();
//        dataMangerRequest.setEmployeeID(Gobal.getUser().getUserID());
//        GetDataManger(dataMangerRequest);
        rycDataregister = (RecyclerView) findViewById(R.id.recyclerdataregistworking);
        linearLayoutManager = new LinearLayoutManager(getApplication());
        backregister = findViewById(R.id.backregister);
        backregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addregisterworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterWorkingActivity.this, AddRegisterWorkingActivity.class);
                startActivity(intent);
            }
        });

        todaydataregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    int mYear = mcurrentDate.get(Calendar.YEAR);
                    int mMonth = mcurrentDate.get(Calendar.MONTH);
                    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog mDatePicker;
                    mDatePicker = new DatePickerDialog(RegisterWorkingActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            // TODO Auto-generated method stub
                            /*      Your code   to get date and time    */
                            selectedmonth = selectedmonth + 1;
                            if (selectedday < 9 && selectedmonth < 9) {
                                todaydataregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                                gobalRegister.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                            } else if (selectedday < 9 && selectedmonth > 9) {
                                todaydataregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                                gobalRegister.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                            } else if (selectedday > 9 && selectedmonth > 9) {
                                todaydataregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                                gobalRegister.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                            } else if (selectedday > 9 && selectedmonth < 9) {
                                todaydataregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                                gobalRegister.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                            }
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.setTitle("Select Date");
                    mDatePicker.show();
            }
        });
        fromdaydataregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(RegisterWorkingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            fromdaydataregister.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalRegister.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromdaydataregister.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalRegister.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromdaydataregister.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalRegister.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromdaydataregister.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalRegister.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        btnsearchregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                RegisterWorkingRequest registerWorkingRequest = new RegisterWorkingRequest();
                registerWorkingRequest.setEmployeeID(com.example.myapplication.Gobal.Gobal.getUser().getUserID());
                registerWorkingRequest.setFromDate(gobalRegister.getFromdayfr());
                registerWorkingRequest.setToDate(gobalRegister.getTodayfr());
                registerWorkingRequest.setPageNumber(pagenumber);
                registerWorkingRequest.setSymbolID(IDSymbol);
                fromdayit = gobalRegister.getFromdayfr();
                todayit = gobalRegister.getTodayfr();
                if (registerWorkingRequest.getEmployeeID().equals("") == true || registerWorkingRequest.getFromDate().equals("") == true || registerWorkingRequest.getToDate().equals("") == true) {
                    dialogcustom("Không Được Bỏ Trống");
                } else {
                    GetDataRegister(registerWorkingRequest);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fromdayit == null || todayit == null) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat abc1 = new SimpleDateFormat("yyyy/MM/dd");
            String a = simpleDateFormat.format(calendar.getTime());
            todaydataregister.setText(a);
            SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
            SimpleDateFormat abc2 = new SimpleDateFormat("yyyy/MM/01");
            String b = abc.format(calendar.getTime());
            fromdaydataregister.setText(b);
            RegisterWorkingRequest registerWorkingRequest = new RegisterWorkingRequest();
            registerWorkingRequest.setEmployeeID(Gobal.getUser().getUserID());
            registerWorkingRequest.setFromDate(abc2.format(calendar.getTime()));
            registerWorkingRequest.setToDate(abc1.format(calendar.getTime()));
            registerWorkingRequest.setPageNumber(pagenumber);
            registerWorkingRequest.setSymbolID(IDSymbol);
            GetDataRegister(registerWorkingRequest);
        } else {
            RegisterWorkingRequest registerWorkingRequest = new RegisterWorkingRequest();
            registerWorkingRequest.setEmployeeID(Gobal.getUser().getUserID());
            registerWorkingRequest.setFromDate(fromdayit);
            registerWorkingRequest.setToDate(todayit);
            registerWorkingRequest.setPageNumber(pagenumber);
            registerWorkingRequest.setSymbolID(IDSymbol);
            GetDataRegister(registerWorkingRequest);
        }
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);

    }

    @Override
    protected void onStart() {
        super.onStart();
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
                            arrayAdapter = new ArrayAdapter(getApplication(), R.layout.spinner_item2, dataSymbols);
                            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                            symbolid.setAdapter(arrayAdapter);
                            IDSymbol = "";
                            symbolid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            }
        });
    }

    public void loadmore(int pagenumber, String fromday, String today) {

        RegisterWorkingRequest registerWorkingRequest = new RegisterWorkingRequest();
        registerWorkingRequest.setEmployeeID(Gobal.getUser().getUserID());
        registerWorkingRequest.setFromDate(fromday);
        registerWorkingRequest.setToDate(today);
        registerWorkingRequest.setPageNumber(pagenumber);
        GetMoreDataRegister(registerWorkingRequest);
    }

    public void GetMoreDataRegister(RegisterWorkingRequest registerWorkingRequest) {
        Call<RegisterWorkingReponse> registerWorkingReponseCall = APIClient.DataRigister().GetDataRegister(Gobal.getGuiID(), registerWorkingRequest);
        registerWorkingReponseCall.enqueue(new Callback<RegisterWorkingReponse>() {
            @Override
            public void onResponse(Call<RegisterWorkingReponse> call, Response<RegisterWorkingReponse> response) {
                if (response.isSuccessful()) {
                    RegisterWorkingReponse registerWorkingReponse = response.body();
                    if (registerWorkingReponse.getData().size()!=0 && registerWorkingReponse.getStatusID()==1) {
                        Datalist.addAll(registerWorkingReponse.getData());
                        adapter.loadmore(registerWorkingReponse.getData());
                    }
                    else {
                        isScrolling=true;

                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterWorkingReponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });

    }

    public void GetDataRegister(RegisterWorkingRequest registerWorkingRequest) {
        Call<RegisterWorkingReponse> registerWorkingReponseCall = APIClient.DataRigister().GetDataRegister(Gobal.getGuiID(), registerWorkingRequest);
        registerWorkingReponseCall.enqueue(new Callback<RegisterWorkingReponse>() {
            @Override
            public void onResponse(Call<RegisterWorkingReponse> call, Response<RegisterWorkingReponse> response) {
                if (response.isSuccessful()) {
                    RegisterWorkingReponse registerWorkingReponse = response.body();
                    if (registerWorkingReponse.getStatusID() == 1) {
                        Datalist.removeAll(Datalist);
                        Datalist.addAll(registerWorkingReponse.getData());
                        adapter = new RegisterWorkingAdapter(Datalist,getApplicationContext());
                        rycDataregister.setAdapter(adapter);
                        rycDataregister.setLayoutManager(linearLayoutManager);
                        rycDataregister.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (!isScrolling) {
                                        if (linearLayoutManager != null || linearLayoutManager.findLastVisibleItemPosition() == Datalist.size() - 1) {
                                            if (pagenumber <  registerWorkingReponse.getTotalPage()) {
                                                pagenumber = pagenumber + 1;
                                                loadmore(pagenumber, gobalRegister.getFromdayfr(), gobalRegister.getTodayfr());
                                            }
                                        }
                                        else {
                                            isScrolling = true;
                                        }
                                    }
                                }
                            }
                        });
                    } else
                        Toast.makeText(getApplication(), "" + registerWorkingReponse.getErrorDescription(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterWorkingReponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối,Vui Lòng Thử Lại Sau");
            }
        });

    }

    public void dialogcustom(String text) {
        Dialog dialog = new Dialog(RegisterWorkingActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
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

    private static class GobalRegister {
        private String fromdayfr;

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

        private String todayfr;
    }
}
