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
import com.example.myapplication.Adpter.WorkingEmployeeAdapter;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataWorkingEmployee;
import com.example.myapplication.R;
import com.example.myapplication.Request.WorkingEmployeeRequest;
import com.example.myapplication.Response.WorkingEmployeeRespone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkingEmployeeActivity extends AppCompatActivity {
    TextView txttoday, txtfromday;
    Button btnsearch;
    private int pagenumber = 1;
    WorkingEmployeeAdapter adapter;
    Boolean isScrolling = false;
    private RecyclerView recyclerView;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    ImageView backworking;
    LinearLayoutManager linearLayoutManager;
    List<DataWorkingEmployee> Datalist = new ArrayList<>();
    GobalEmployee gobalEmployee;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_workingemployee);
        btnsearch = findViewById(R.id.btnsearch);
        super.onCreate(savedInstanceState);
        gobalEmployee  = new GobalEmployee();
        backworking = findViewById(R.id.backworking);
        backworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtfromday = findViewById(R.id.fromdayworking);
        txttoday = findViewById(R.id.todayworking);
        recyclerView = findViewById(R.id.recyclerWorkingEmployee);

        linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);

        //tùy chỉnh cố slg item trong rycle
        recyclerView.setItemViewCacheSize(Datalist.size());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        gobalEmployee.setTodayFr(simpleDateFormat.format(calendar.getTime()));
        String a = simpleDateFormat1.format(calendar.getTime());
        txttoday.setText(a);
        SimpleDateFormat abc = new SimpleDateFormat("yyyy/MM/01");
        SimpleDateFormat abc1 = new SimpleDateFormat("01/MM/yyyy");
        gobalEmployee.setFromdayFr(abc.format(calendar.getTime()));
        String b = abc1.format(calendar.getTime());
        txtfromday.setText(b);
        WorkingEmployeeRequest workingEmployeeRequest = new WorkingEmployeeRequest();
        workingEmployeeRequest.setEmployeeID(Gobal.getUser().getUserID());
        workingEmployeeRequest.setPageNumber(pagenumber);
        workingEmployeeRequest.setFromDate(gobalEmployee.getFromdayFr());
        workingEmployeeRequest.setToDate(gobalEmployee.getTodayFr());
        GetWorkingEmployee(workingEmployeeRequest);
        txtfromday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(WorkingEmployeeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            txtfromday.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            txtfromday.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            txtfromday.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setFromdayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            txtfromday.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setFromdayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        txttoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(WorkingEmployeeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            txttoday.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            txttoday.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            txttoday.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setTodayFr("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            txttoday.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            gobalEmployee.setTodayFr("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagenumber = 1;
                WorkingEmployeeRequest workingEmployeeRequest = new WorkingEmployeeRequest();
                workingEmployeeRequest.setEmployeeID(Gobal.getUser().getUserID());
                workingEmployeeRequest.setPageNumber(pagenumber);
                workingEmployeeRequest.setFromDate(gobalEmployee.getFromdayFr());
                workingEmployeeRequest.setToDate(gobalEmployee.getTodayFr());
                GetWorkingEmployee(workingEmployeeRequest);
            }
        });


    }

    public void loadMore(int pagenumber, String fromday, String today) {
        WorkingEmployeeRequest workingEmployeeRequest = new WorkingEmployeeRequest();
        workingEmployeeRequest.setEmployeeID(Gobal.getUser().getUserID());
        workingEmployeeRequest.setPageNumber(pagenumber);
        workingEmployeeRequest.setFromDate(fromday);
        workingEmployeeRequest.setToDate(today);
        GetMoreEmPloyeeWorking(workingEmployeeRequest);

    }

    public void GetMoreEmPloyeeWorking(WorkingEmployeeRequest workingEmployeeRequest) {
        Call<WorkingEmployeeRespone> workingEmployeeResponeCall = APIClient.DataWorkingEmployee().GetDataWorkingEmployee(Gobal.getGuiID(), workingEmployeeRequest);
        workingEmployeeResponeCall.enqueue(new Callback<WorkingEmployeeRespone>() {
            @Override
            public void onResponse(Call<WorkingEmployeeRespone> call, Response<WorkingEmployeeRespone> response) {

                if (response.isSuccessful()) {
                    WorkingEmployeeRespone data = response.body();
                    if (data.getStatusID() == 1 && data.getData().size() != 0) {
                        adapter.loadmore(data.getData());
                        Datalist.addAll(data.getData());
                    } else {
                        isScrolling = true;
                    }
                }
            }

            public void onFailure(Call<WorkingEmployeeRespone> call, Throwable t) {
            }
        });

    }

    public void GetWorkingEmployee(WorkingEmployeeRequest workingEmployeeRequest) {
        Call<WorkingEmployeeRespone> workingEmployeeResponeCall = APIClient.DataWorkingEmployee().GetDataWorkingEmployee(Gobal.getGuiID(), workingEmployeeRequest);
        workingEmployeeResponeCall.enqueue(new Callback<WorkingEmployeeRespone>() {
            @Override
            public void onResponse(Call<WorkingEmployeeRespone> call, Response<WorkingEmployeeRespone> response) {
                if (response.isSuccessful()) {
                    try {
                        WorkingEmployeeRespone workingEmployeeRespone = response.body();
                        if (workingEmployeeRespone.getStatusID() == 1) {
                            Datalist.removeAll(Datalist);
                            Datalist.addAll(workingEmployeeRespone.getData());
                            adapter = new WorkingEmployeeAdapter(Datalist, getApplication());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);
                                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                        if (!isScrolling) {
                                            if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == Datalist.size() - 1) {
                                                if (pagenumber < Gobal.getTotalpage()) {
                                                    pagenumber = pagenumber + 1;
                                                    loadMore(pagenumber, gobalEmployee.getFromdayFr(), gobalEmployee.getTodayFr());
                                                }
                                                else {
                                                    isScrolling=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            });
                        } else if (workingEmployeeRespone.getStatusID() == -1) {
                            Toast.makeText(getApplication(), "" + workingEmployeeRespone.getErrorDescription(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception exception) {

                    }
                }
            }

            @Override
            public void onFailure(Call<WorkingEmployeeRespone> call, Throwable t) {
                dialogcustom("Không Thể Kết Nối");
            }
        });

    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(WorkingEmployeeActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
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

    public static class GobalEmployee {
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

