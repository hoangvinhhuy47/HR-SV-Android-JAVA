package com.example.myapplication.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Fragment.HomeActivity;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.SettingConfig;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Request.CheckinRequest;
import com.example.myapplication.Response.CheckInReponse;
import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Request.LoadBeginCheckInRequest;
import com.example.myapplication.Utils.ExampleService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "HR_SOFTWAREVIET";
    DBHelper db;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        db = new DBHelper(this);
        SettingConfig settingConfig = db.GetConfig();
        User user = db.GetUser();
        if (settingConfig != null && settingConfig.getConfigID() != 0) {
            if (user != null && user.getUserID() != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Gobal.setGuiID(settingConfig.getGuiID());
                        Gobal.setWebAPI(settingConfig.getWebAPI());
                        Gobal.setUser(user);
                        Gobal.setUserID(user.getUserID());
                        Gobal.getUser().getUserID();
                        LoadBeginCheckInRequest loadBeginCheckInRequest = new LoadBeginCheckInRequest();
                        loadBeginCheckInRequest.setEmployeeID(Gobal.getUser().getUserID());
                        LoadBeginCheckIn(loadBeginCheckInRequest);
                    }
                }, 2000);

            } else {
                Gobal.setGuiID(settingConfig.getGuiID());
                Gobal.setWebAPI(settingConfig.getWebAPI());
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Intent intent = new Intent(MainActivity.this, SettingConfigActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void LoadBeginCheckIn(LoadBeginCheckInRequest loadBeginCheckInRequest) {
        Call<LoadBeginCheckInResponse> loadBeginCheckInResponseCall = APIClient.LoadBegin().GetLoadBegin(Gobal.getGuiID(), loadBeginCheckInRequest);
        loadBeginCheckInResponseCall.enqueue(new Callback<LoadBeginCheckInResponse>() {
            @Override
            public void onResponse(Call<LoadBeginCheckInResponse> call, Response<LoadBeginCheckInResponse> response) {
                if (response.isSuccessful()) {
                    LoadBeginCheckInResponse loadBeginCheckInResponse = response.body();
                    if (loadBeginCheckInResponse.getStatusID() == 1) {
                        Gobal.setIsCheckIn(loadBeginCheckInResponse.getData().isCheckIn());
                        Gobal.setCheckTime(loadBeginCheckInResponse.getData().getCheckTime());
                        Gobal.setShiftName(loadBeginCheckInResponse.getData().getShiftName());
                        Intent intent1 = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent1);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoadBeginCheckInResponse> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối");
            }
        });
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
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void dialogcustom(String text) {
        final Dialog dialog = new Dialog(MainActivity.this);
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


}
