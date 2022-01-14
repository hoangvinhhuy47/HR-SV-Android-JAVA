package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Adpter.WorkingAttendaceAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataWorkingAttendace;
import com.example.myapplication.R;
import com.example.myapplication.Response.DataWorkingAttendaceRespone;
import com.example.myapplication.Request.WorkingAttendaceRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkingAttendanceActivity extends AppCompatActivity {
    TextView txtFromDay, txtToDay;
    Button SearchWorking;
    Boolean isScrolling = false;
    private int pagenumber = 1;
    ImageView backattendance;
    WorkingAttendaceAdapter adapter;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    List<DataWorkingAttendace> Datalist = new ArrayList<>();
    GobalAttendance gobalAttendance;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_dataworkingattendance);
        super.onCreate(savedInstanceState);
        gobalAttendance = new GobalAttendance();
        recyclerView = findViewById(R.id.recyclerdataWorkingAttendace);
        linearLayoutManager = new LinearLayoutManager(getApplication(),RecyclerView.VERTICAL,false);
        txtFromDay = findViewById(R.id.FromDay);
        txtToDay = findViewById(R.id.today);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String a = format.format(calendar.getTime());
        gobalAttendance.setTodayFr(simpleDateFormat.format(calendar.getTime()));
        txtToDay.setText(a);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/01");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("01/MM/yyyy");
        String b = simpleDateFormat1.format(calendar.getTime());
        gobalAttendance.setFromdayFr(format1.format(calendar.getTime()));
        txtFromDay.setText(b);
        WorkingAttendaceRequest workingAttendaceRequest = new WorkingAttendaceRequest();
        workingAttendaceRequest.setEmployeeID(Gobal.getUser().getUserID());
        workingAttendaceRequest.setPageNumber(pagenumber);
        workingAttendaceRequest.setFromDate(gobalAttendance.getFromdayFr());
        workingAttendaceRequest.setToDate(gobalAttendance.getTodayFr());
        GetWorkingAttendace(workingAttendaceRequest);
        backattendance = findViewById(R.id.backattendance);
        backattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtFromDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(WorkingAttendanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            txtFromDay.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            txtFromDay.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            txtFromDay.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            txtFromDay.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        txtToDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(WorkingAttendanceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            txtToDay.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            txtToDay.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            txtToDay.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalAttendance.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            txtToDay.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear) ;
                            gobalAttendance.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        SearchWorking = findViewById(R.id.SearchWorking);
        SearchWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                WorkingAttendaceRequest workingAttendaceRequest = new WorkingAttendaceRequest();
                workingAttendaceRequest.setEmployeeID(Gobal.getUser().getUserID());
                workingAttendaceRequest.setPageNumber(pagenumber);
                workingAttendaceRequest.setFromDate(gobalAttendance.getFromdayFr());
                workingAttendaceRequest.setToDate(gobalAttendance.getTodayFr());
                GetWorkingAttendace(workingAttendaceRequest);
            }
        });


    }

    public void loadMore(int pagenumber, String Fromday, String today) {
        WorkingAttendaceRequest workingAttendaceRequest = new WorkingAttendaceRequest();
        workingAttendaceRequest.setEmployeeID(Gobal.getUser().getUserID());
        workingAttendaceRequest.setPageNumber(pagenumber);
        workingAttendaceRequest.setFromDate(Fromday);
        workingAttendaceRequest.setToDate(today);
        GetMoreWorkingAttendace(workingAttendaceRequest);

    }

    public void GetMoreWorkingAttendace(WorkingAttendaceRequest workingAttendaceRequest) {
        Call<DataWorkingAttendaceRespone> dataWorkingAttendaceResponeCall = APIClient.DataWorkingAttendance().GetDataWorking(Gobal.getGuiID(), workingAttendaceRequest);
        dataWorkingAttendaceResponeCall.enqueue(new Callback<DataWorkingAttendaceRespone>() {
            @Override
            public void onResponse(Call<DataWorkingAttendaceRespone> call, Response<DataWorkingAttendaceRespone> response) {

                if (response.isSuccessful()) {
                    DataWorkingAttendaceRespone data = response.body();
                    if (data.getStatusID() == 1 && data.getData().size() != 0) {
                        adapter.loadmore(data.getData());
                        Datalist.addAll(data.getData());
                    } else {
                        isScrolling = true;
                    }

                }
            }

            @Override
            public void onFailure(Call<DataWorkingAttendaceRespone> call, Throwable t) {
            }
        });

    }

    public void GetWorkingAttendace(WorkingAttendaceRequest workingAttendaceRequest) {
        Call<DataWorkingAttendaceRespone> dataWorkingAttendaceResponeCall = APIClient.DataWorkingAttendance().GetDataWorking(Gobal.getGuiID(), workingAttendaceRequest);
        dataWorkingAttendaceResponeCall.enqueue(new Callback<DataWorkingAttendaceRespone>() {
            @Override
            public void onResponse(Call<DataWorkingAttendaceRespone> call, Response<DataWorkingAttendaceRespone> response) {
                if (response.isSuccessful()) {
                    try {
                        DataWorkingAttendaceRespone dataWorkingAttendaceRespone = response.body();
                        if (dataWorkingAttendaceRespone.getStatusID() == 1) {
                            Datalist.removeAll(Datalist);
                            Datalist.addAll(dataWorkingAttendaceRespone.getData());
                            Gobal.setTotalpage(dataWorkingAttendaceRespone.getTotalPage());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new WorkingAttendaceAdapter(Datalist, getApplication());
                            recyclerView.setAdapter(adapter);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (!isScrolling) {
                                            if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == Datalist.size() - 1) {
                                                if (pagenumber <  dataWorkingAttendaceRespone.getTotalPage()) {
                                                    pagenumber = pagenumber + 1;
                                                    loadMore(pagenumber,gobalAttendance.getFromdayFr(),gobalAttendance.getTodayFr());
                                                }
                                                else {
                                                    isScrolling=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getApplication(), "" + dataWorkingAttendaceRespone.getErrorDescription(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception exception) {
                    }
                }
            }

            @Override
            public void onFailure(Call<DataWorkingAttendaceRespone> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối, Vui Lòng Thử Lại Sau");
            }
        });

    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(WorkingAttendanceActivity.this);
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

    public static class GobalAttendance {
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
