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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.LeaveApplicationAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataLeaveAplication;
import com.example.myapplication.Model.DataSymbol;
import com.example.myapplication.R;
import com.example.myapplication.Request.GetDataSymbolRequest;
import com.example.myapplication.Request.LeaveApplicationRequest;
import com.example.myapplication.Response.GetDataSymbolResponse;
import com.example.myapplication.Response.LeaveApplicationResopnse;
import com.example.myapplication.Utils.GobalUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApplicationActivity extends AppCompatActivity {
    TextView todayleave, fromdayleave;
    Button AddLeave, searchLeave;
    private int pagenumber = 1;
    Boolean isScrolling = false;
    LeaveApplicationAdapter adapter;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ImageView imgexit, backleave;
    Button btnext;
    TextView ttvTitle;
    String fromdayit, todayit;
    ArrayAdapter arrayAdapter;
    List<DataLeaveAplication> Datalist = new ArrayList<>();
    GobalLeaveApplication gobalLeaveApplication = new GobalLeaveApplication();
    Spinner symbolid;
    String IDSymbol = "";
    SimpleDateFormat abc1;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat1;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_leaveapplitcation);
        super.onCreate(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
        Intent intent = this.getIntent();
        symbolid = findViewById(R.id.symbolid);
        todayleave = findViewById(R.id.todayleave);
        fromdayleave = findViewById(R.id.fromdayleave);
        GetDataSymbolRequest getDataSymbolRequest = new GetDataSymbolRequest();
        GetDataSymbol(getDataSymbolRequest);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
     simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        String a = simpleDateFormat.format(calendar.getTime());
        gobalLeaveApplication.setTodayfr(simpleDateFormat1.format(calendar.getTime()));
        todayleave.setText(a);
        SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
        abc1 = new SimpleDateFormat("yyyy/MM/01");
        String b = abc.format(calendar.getTime());
        gobalLeaveApplication.setFromdayfr(abc1.format(calendar.getTime()));
        fromdayleave.setText(b);
        recyclerView = findViewById(R.id.recyclerleaveapplication);
        backleave = findViewById(R.id.backleave);
        backleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        todayleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(LeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            todayleave.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            todayleave.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            todayleave.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setTodayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            todayleave.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setTodayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        fromdayleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(LeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                            fromdayleave.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            fromdayleave.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            fromdayleave.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setFromdayfr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            fromdayleave.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalLeaveApplication.setFromdayfr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        AddLeave = findViewById(R.id.AddLeave);
        AddLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaveApplicationActivity.this, AddLeaveApplicationActivity.class);
                startActivity(intent);
            }
        });

        searchLeave = findViewById(R.id.searchLeave);
        searchLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
                leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
                leaveApplicationRequest.setFromDate(gobalLeaveApplication.getFromdayfr());
                leaveApplicationRequest.setToDate(gobalLeaveApplication.getTodayfr());
                leaveApplicationRequest.setPageNumber(pagenumber);
                leaveApplicationRequest.setSymbolID(IDSymbol);
                GetDataLeaveAplication(leaveApplicationRequest);
                fromdayit = gobalLeaveApplication.getFromdayfr();
                todayit = gobalLeaveApplication.getTodayfr();
//                        Gobal.setFromdayint(gobalLeaveApplication.getFromdayfr());
//                        Gobal.setTodayint(gobalLeaveApplication.getTodayfr());
            }
        });
    }
    public void loadMore(int pagenumber, String fromday, String today) {
        LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
        leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
        leaveApplicationRequest.setPageNumber(pagenumber);
        leaveApplicationRequest.setFromDate(fromday);
        leaveApplicationRequest.setToDate(today);
        GetMoreEmPloyeeWorking(leaveApplicationRequest);

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fromdayit == null || todayit == null) {
            calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat1= new SimpleDateFormat("yyyy/MM/dd");
            String a = simpleDateFormat.format(calendar.getTime());
            todayleave.setText(a);
            SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
            abc1= new SimpleDateFormat("yyyy/MM/01");
            String b = abc.format(calendar.getTime());
            fromdayleave.setText(b);
            LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
            leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
            leaveApplicationRequest.setFromDate(abc1.format(calendar.getTime()));
            leaveApplicationRequest.setToDate(simpleDateFormat1.format(calendar.getTime()));
            leaveApplicationRequest.setPageNumber(pagenumber);
            leaveApplicationRequest.setSymbolID("");
            GetDataLeaveAplication(leaveApplicationRequest);
        } else {
            LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
            leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
            leaveApplicationRequest.setFromDate(fromdayit);
            leaveApplicationRequest.setToDate(todayit);
            leaveApplicationRequest.setPageNumber(pagenumber);
            leaveApplicationRequest.setSymbolID("");
            GetDataLeaveAplication(leaveApplicationRequest);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (fromdayit == null || todayit == null) {
           calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat1= new SimpleDateFormat("yyyy/MM/dd");
            String a = simpleDateFormat.format(calendar.getTime());
            todayleave.setText(a);
            SimpleDateFormat abc = new SimpleDateFormat("01/MM/yyyy");
           abc1= new SimpleDateFormat("yyyy/MM/01");
            String b = abc.format(calendar.getTime());
            fromdayleave.setText(b);
            LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
            leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
            leaveApplicationRequest.setFromDate(abc1.format(calendar.getTime()));
            leaveApplicationRequest.setToDate(simpleDateFormat1.format(calendar.getTime()));
            leaveApplicationRequest.setPageNumber(pagenumber);
            leaveApplicationRequest.setSymbolID("");
            GetDataLeaveAplication(leaveApplicationRequest);
        } else {
            LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
            leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
            leaveApplicationRequest.setFromDate(fromdayit);
            leaveApplicationRequest.setToDate(todayit);
            leaveApplicationRequest.setPageNumber(pagenumber);
            leaveApplicationRequest.setSymbolID("");
            GetDataLeaveAplication(leaveApplicationRequest);
        }
    }

    public void GetMoreEmPloyeeWorking(LeaveApplicationRequest leaveApplicationRequest) {
        Call<LeaveApplicationResopnse> leaveApplicationResopnseCall = APIClient.DataLeaveAplication().GetDataLeaveApplication(Gobal.getGuiID(), leaveApplicationRequest);
        leaveApplicationResopnseCall.enqueue(new Callback<LeaveApplicationResopnse>() {
            @Override
            public void onResponse(Call<LeaveApplicationResopnse> call, Response<LeaveApplicationResopnse> response) {

                if (response.isSuccessful()) {
                    LeaveApplicationResopnse data = response.body();
                    if (data.getStatusID() == 1 && data.getData().size()!=0) {

                        adapter.loadmore(data.getData());
                        Datalist.addAll(data.getData());
                    }
                    else {
                        isScrolling = true;
                    }
                }
            }

            public void onFailure(Call<LeaveApplicationResopnse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối,Vui lòng thử lại sau");
            }
        });

    }

    public void GetDataLeaveAplication(LeaveApplicationRequest leaveApplicationRequest) {
        Call<LeaveApplicationResopnse> leaveApplicationResopnseCall = APIClient.DataLeaveAplication().GetDataLeaveApplication(Gobal.getGuiID(), leaveApplicationRequest);
        leaveApplicationResopnseCall.enqueue(new Callback<LeaveApplicationResopnse>() {
            @Override
            public void onResponse(Call<LeaveApplicationResopnse> call, Response<LeaveApplicationResopnse> response) {
                if (response.isSuccessful()) {
                    LeaveApplicationResopnse leaveApplicationResopnse = response.body();
                    if (leaveApplicationResopnse.getStatusID() == 1) {
                        Datalist.removeAll(Datalist);
                        Datalist.addAll(leaveApplicationResopnse.getData());
                        Gobal.setTotalpage(leaveApplicationResopnse.getTotalPage());
                        adapter = new LeaveApplicationAdapter(Datalist, getApplicationContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    if (!isScrolling) {
                                        if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == Datalist.size() - 1) {
                                            if (pagenumber < Gobal.getTotalpage()) {
                                                pagenumber = pagenumber + 1;
                                                loadMore(pagenumber, gobalLeaveApplication.getFromdayfr(), gobalLeaveApplication.getTodayfr());
                                            }
                                        } else {
                                            isScrolling = true;
                                        }
                                    }
                                }
                            }
                        });

                    } else if (leaveApplicationResopnse.getStatusID() == -1) {
                        dialogcustom(leaveApplicationResopnse.ErrorDescription);
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaveApplicationResopnse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
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
                                            searchLeave.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    pagenumber = 1;
                                                    LeaveApplicationRequest leaveApplicationRequest = new LeaveApplicationRequest();
                                                    leaveApplicationRequest.setEmployeeID(Gobal.getUser().getUserID());
                                                    leaveApplicationRequest.setFromDate(gobalLeaveApplication.getFromdayfr());
                                                    leaveApplicationRequest.setToDate(gobalLeaveApplication.getTodayfr());
                                                    leaveApplicationRequest.setPageNumber(pagenumber);
                                                    leaveApplicationRequest.setSymbolID(IDSymbol);
                                                    GetDataLeaveAplication(leaveApplicationRequest);
                                                    fromdayit = gobalLeaveApplication.getFromdayfr();
                                                    todayit = gobalLeaveApplication.getTodayfr();

                                                }
                                            });

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

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(LeaveApplicationActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

    private static class GobalLeaveApplication {
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
}

