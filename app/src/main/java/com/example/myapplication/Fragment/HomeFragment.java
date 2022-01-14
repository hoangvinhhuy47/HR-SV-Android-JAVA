
package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Activity.RegisterWorkingActivity;
import com.example.myapplication.Activity.WorkingAttendanceActivity;
import com.example.myapplication.Activity.LeaveApplicationActivity;

import com.example.myapplication.Activity.WorkingEmployeeActivity;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Request.LoadBeginCheckInRequest;
import com.example.myapplication.Response.CheckInReponse;
import com.example.myapplication.Request.CheckinRequest;
import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Utils.ExampleService;
import com.example.myapplication.Utils.GobalUtils;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    @Nullable
    ImageView imgexit;
    Button btnexit;
    TextView ttvTitle;
    RelativeLayout CheckInTime;
    TextView textclock, txtShiftname, textcheckin;
    DBHelper db;
    ProgressBar pro_checkin;
    RelativeLayout leaveapplication, Register, DataWorkingAttendace, workingemployee;
    private static final String CHANNEL_ID = "HR_SOFTWAREVIET";
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    private static final String EXTRA_NOTIFICATION_ID = "1111";
    private Location location;
    // Đối tượng tương tác với Google API
    private GoogleApiClient gac;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_working, container, false);
        textcheckin = view.findViewById(R.id.textcheckin);

        db = new DBHelper(getContext());
        pro_checkin=view.findViewById(R.id.pro_checkin);
        leaveapplication = (RelativeLayout) view.findViewById(R.id.leaveapplication);
        leaveapplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LeaveApplicationActivity.class);
                startActivity(intent);
            }
        });
        Register = (RelativeLayout) view.findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterWorkingActivity.class);
                startActivity(intent);
            }
        });
        DataWorkingAttendace = (RelativeLayout) view.findViewById(R.id.DataWorkingAttendace);
        DataWorkingAttendace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WorkingAttendanceActivity.class);
                startActivity(intent);
            }
        });
        textclock = (TextView) view.findViewById(R.id.textclock);
        txtShiftname = (TextView) view.findViewById(R.id.shiftname);
        CheckInTime = (RelativeLayout) view.findViewById(R.id.CheckInTime);
        if (Gobal.getIsCheckIn() == true) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home_vc));
            textcheckin.setText("Ra Ca  ");
            textcheckin.setTextColor(Color.RED);
            textclock.setTextColor(Color.RED);
            txtShiftname.setTextColor(Color.RED);
            textclock.setText(GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(Gobal.getCheckTime())));
            txtShiftname.setText(Gobal.getShiftName());

        } else if (Gobal.getIsCheckIn() == false) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home));
            textcheckin.setText("Vào Ca  ");
            textcheckin.setTextColor(Color.parseColor("#111111"));

        }
        DBHelper db = new DBHelper(getActivity());
        final User user = db.GetUser();


        CheckInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_checkintime);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
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
                        pro_checkin.setVisibility(View.VISIBLE);
//                        showload();
                        CheckinRequest checkinRequest = new CheckinRequest();
                        checkinRequest.setEmployeeID(user.getUserID());
                        CheckIn(checkinRequest);
                        dialog.dismiss();
//                        hide();
                    }
                });

            }
        });

        workingemployee = (RelativeLayout) view.findViewById(R.id.workingemployee);
        workingemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WorkingEmployeeActivity.class);
                startActivity(intent);
            }
        });
        view.getContext();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (Gobal.getIsCheckIn() == true) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home_vc));
            textcheckin.setText("Ra Ca");
            textcheckin.setTextColor(Color.RED);
            textclock.setTextColor(Color.RED);
            txtShiftname.setTextColor(Color.RED);
            textclock.setText(GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(Gobal.getCheckTime())));
            txtShiftname.setText(Gobal.getShiftName());

        } else if (Gobal.getIsCheckIn() == false) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home));
            textcheckin.setText("Vào Ca");
            textcheckin.setTextColor(Color.parseColor("#111111"));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Gobal.getIsCheckIn() == true) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home_vc));
            textcheckin.setText("Ra Ca  ");
            textcheckin.setTextColor(Color.RED);
            textclock.setTextColor(Color.RED);
            txtShiftname.setTextColor(Color.RED);
            textclock.setText(GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(Gobal.getCheckTime())));
            txtShiftname.setText(Gobal.getShiftName());

        } else if (Gobal.getIsCheckIn() == false) {
            CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home));
            textcheckin.setText("Vào Ca  ");
            textcheckin.setTextColor(Color.parseColor("#111111"));

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        LoadBeginCheckInRequest loadBeginCheckInRequest = new LoadBeginCheckInRequest();
//        loadBeginCheckInRequest.setEmployeeID(Gobal.getUser().getUserID());
//        LoadBeginCheckIn(loadBeginCheckInRequest);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadBeginCheckInRequest loadBeginCheckInRequest = new LoadBeginCheckInRequest();
        loadBeginCheckInRequest.setEmployeeID(Gobal.getUser().getUserID());
        LoadBeginCheckIn(loadBeginCheckInRequest);

    }

    public void LoadBeginCheckIn(LoadBeginCheckInRequest loadBeginCheckInRequest) {
        Call<LoadBeginCheckInResponse> loadBeginCheckInResponseCall = APIClient.LoadBegin().GetLoadBegin(Gobal.getGuiID(), loadBeginCheckInRequest);
        loadBeginCheckInResponseCall.enqueue(new Callback<LoadBeginCheckInResponse>() {
            @Override
            public void onResponse(Call<LoadBeginCheckInResponse> call, Response<LoadBeginCheckInResponse> response) {
                if (response.isSuccessful()) {
                    LoadBeginCheckInResponse loadBeginCheckInResponse = response.body();
                    if (loadBeginCheckInResponse.getStatusID()==1) {
                        Gobal.setIsCheckIn(loadBeginCheckInResponse.getData().isCheckIn());
                        Gobal.setCheckTime(loadBeginCheckInResponse.getData().getCheckTime());
                        Gobal.setShiftName(loadBeginCheckInResponse.getData().getShiftName());
                    }
                    else {
                        Toast.makeText(getActivity(),"Lỗi Dữ liệu",Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<LoadBeginCheckInResponse> call, Throwable t) {
                // dialogcustom("Lỗi Hệ Thống!");
            }
        });
    }

    public void CheckIn(CheckinRequest checkinRequest) {
        Call<CheckInReponse> checkInReponseCall = APIClient.CheckInService().GetCheckIn(Gobal.getGuiID(), checkinRequest);
        checkInReponseCall.enqueue(new Callback<CheckInReponse>() {
            @Override
            public void onResponse(Call<CheckInReponse> call, Response<CheckInReponse> response) {
                showload();
                if (response.isSuccessful()) {
                    pro_checkin.setVisibility(View.GONE);
                    hide();
                    CheckInReponse checkInReponse = response.body();
                    Gobal.setCheckTime(checkInReponse.getData().getCheckTime());
                    Gobal.setShiftName(checkInReponse.getData().getShiftName());
                    Gobal.setIsCheckIn(checkInReponse.getData().isCheckIn());
                    Time time = Time.valueOf(GobalUtils.convertDateToString(GobalUtils.convertStringToDate(checkInReponse.getData().getDateTimeOut()), "HH:mm:ss"));
                    int a = GobalUtils.ChangeToTime(time.getHours(), time.getMinutes(), time.getSeconds());
                    // thời gian out
                    textclock.setText(GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(GobalCheckIn.getCheckTime())));
                    txtShiftname.setText(GobalCheckIn.getShiftName());
                    if (checkInReponse.getData().isCheckIn() == true) {
                        textcheckin.setText("Ra Ca   ");
                        Dialog dialogerroo = new Dialog(getActivity());
                        dialogerroo.setContentView(R.layout.alert_dialog);
                        TextView ttvinfo = dialogerroo.findViewById(R.id.ttvTitle);
                        ImageView exiterro = dialogerroo.findViewById(R.id.exit);
                        Button accepterro = dialogerroo.findViewById(R.id.accept);
                        dialogerroo.getWindow().setGravity(Gravity.CENTER);
                        dialogerroo.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dialogerroo.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                        dialogerroo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogerroo.show();
                        ttvinfo.setText("Vào Ca Thành Công");
                        exiterro.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerroo.dismiss();
                            }
                        });
                        accepterro.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerroo.dismiss();
                            }
                        });
                        CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home_vc));
                        textcheckin.setTextColor(Color.RED);
                        textclock.setTextColor(Color.RED);
                        txtShiftname.setTextColor(Color.RED);
                        Gobal.setIsCheckIn(checkInReponse.getData().isCheckIn());
                        Gobal.setCheckTime(checkInReponse.getData().getCheckTime());
                        Gobal.setShiftName(checkInReponse.getData().getShiftName());
                        textcheckin.setText("Ra Ca ");

                        textclock.setText(GobalUtils.convertTimeShowScreen(GobalUtils.convertStringToDate(Gobal.getCheckTime())));
                        txtShiftname.setText(Gobal.getShiftName());
                        String input = "Runnning";
                        Intent serviceIntent = new Intent(getActivity(), ExampleService.class);
                        serviceIntent.putExtra("inputExtra", input);
                        ContextCompat.startForegroundService(getActivity(), serviceIntent);
                        new CountDownTimer(1000, 10) {
                            @Override
                            public void onTick(long l) {

                            }

                            @SuppressLint("NewApi")
                            @Override
                            public void onFinish() {
                                createNotificationChannel();
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
                                Intent snoozeIntent = new Intent(getActivity(), HomeActivity.class);
                                ;
                                snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
                                Intent intent = new Intent(getContext(), HomeActivity.class);
                                PendingIntent pIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
                                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                                        .setSmallIcon(R.drawable.location)
                                        .setContentTitle("Bạn Hãy Check Out Đi")
                                        .setContentText("Giờ làm của bạn đã đến ... !!!")
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText("Giờ làm của bạn đã đến ... !!!"))
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        .addAction(R.drawable.location, "Go To", pIntent);
                                notificationManager.notify(1, mBuilder.build());
                                String replyLabel = getResources().getString(R.string.reply_label);
                                @SuppressLint({"NewApi", "LocalSuppress"})
                                RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                                        .setLabel(replyLabel)
                                        .build();

                            }
                        }.start();
                    } else if (checkInReponse.getData().isCheckIn() == false) {
                        textcheckin.setText("Vào Ca ");
                        Dialog dialogerroo = new Dialog(getActivity());
                        dialogerroo.setContentView(R.layout.alert_dialog);
                        TextView ttvinfo = dialogerroo.findViewById(R.id.ttvTitle);
                        ImageView exiterro = dialogerroo.findViewById(R.id.exit);
                        Button accepterro = dialogerroo.findViewById(R.id.accept);
                        dialogerroo.getWindow().setGravity(Gravity.CENTER);
                        dialogerroo.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dialogerroo.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
                        dialogerroo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialogerroo.show();
                        ttvinfo.setText("Ra Ca Thành Công");
                        exiterro.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerroo.dismiss();
                            }
                        });
                        accepterro.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogerroo.dismiss();
                            }
                        });
                        Gobal.setIsCheckIn(checkInReponse.getData().isCheckIn());
                        CheckInTime.setBackground(getContext().getResources().getDrawable(R.drawable.backgroud_home));
                        textcheckin.setTextColor(Color.parseColor("#111111"));
                        Intent serviceIntent = new Intent(getActivity(), ExampleService.class);
                        getActivity().stopService(serviceIntent);
                    }
                }
                hide();
            }

            @Override
            public void onFailure(Call<CheckInReponse> call, Throwable t) {
                dialogcustom("Lỗi Hệ Thống!");
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static class GobalCheckIn {
        public static String getCheckTime() {
            return CheckTime;
        }

        public static void setCheckTime(String checkTime) {
            CheckTime = checkTime;
        }

        public static String getShiftName() {
            return ShiftName;
        }

        public static void setShiftName(String shiftName) {
            ShiftName = shiftName;
        }

        public static boolean isIsCheckIn() {
            return IsCheckIn;
        }

        public static void setIsCheckIn(boolean isCheckIn) {
            IsCheckIn = isCheckIn;
        }

        private static String CheckTime;
        private static String ShiftName;
        private static boolean IsCheckIn;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Thông Báo";
            String description = "HR_SOFTWARE";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.alert_dialog);
        imgexit = dialog.findViewById(R.id.exit);
        btnexit = dialog.findViewById(R.id.accept);
        ttvTitle = dialog.findViewById(R.id.ttvTitle);
        ttvTitle.setText(text);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btnexit.setOnClickListener(new View.OnClickListener() {
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

    public void showload() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.layout_loading);
        dialog.show();
        dialog.dismiss();
    }

    public void hide() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.layout_loading);
        dialog.show();
        dialog.dismiss();
    }


}


