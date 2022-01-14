package com.example.myapplication.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.SettingConfig;
import com.example.myapplication.Request.LoadBeginCheckInRequest;
import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Response.SettingConfigRepose;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.Request.SettingConfigRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingConfigActivity extends AppCompatActivity {
    EditText edtwebapi;
    EditText edtSiteID;
    EditText edtStoreID;
    DBHelper db;
    Button btnClose, btnOk;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    ProgressBar loader;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.acitivity_settingconfig);
        super.onCreate(savedInstanceState);
        loader = findViewById(R.id.loader);
        db = new DBHelper(this);
        findControlById();
        SettingConfig settingConfig = db.GetConfig();
        if (settingConfig != null) {
            if (settingConfig.getConfigID()!=0) {
//            db.laydulieu();
                Gobal.setGuiID(settingConfig.getGuiID());
                Gobal.setWebAPI(settingConfig.getWebAPI());
                Intent intent = new Intent(SettingConfigActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });

            btnOk.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (TextUtils.isEmpty(edtSiteID.getText().toString()) || TextUtils.isEmpty(edtStoreID.getText().toString())) {
                        dialogcustom("Không được để trống!");
                    } else {
                        loader.setVisibility(View.VISIBLE);
                        Gobal.setWebAPI("https://"+edtwebapi.getText().toString()+"/api/hr/");
                        SettingConfigRequest settingConfigRequest = new SettingConfigRequest();
                        settingConfigRequest.setSiteID(edtSiteID.getText().toString());
                        settingConfigRequest.setDeviceName(edtStoreID.getText().toString());
                        CheckConfig(settingConfigRequest);

                    }
                }
            });


    }


    private void findControlById() {
        edtwebapi = findViewById(R.id.txtWebAPI);
        edtStoreID = findViewById(R.id.edtStoreID);
        edtSiteID = findViewById(R.id.edtSiteID);
        btnClose = findViewById(R.id.btnClose);
        btnOk = findViewById(R.id.btnOk);
    }

    private void CheckConfig(SettingConfigRequest settingConfigRequest) {

        final Call<SettingConfigRepose> settingConfigCall = APIClient.getSettingConfigSerVice().SettingConfig(settingConfigRequest);
        settingConfigCall.enqueue(new Callback<SettingConfigRepose>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<SettingConfigRepose> call, Response<SettingConfigRepose> response) {
                if (response.isSuccessful()) {

                    SettingConfigRepose settingConfigRepose = response.body();

                    if (settingConfigRepose.getStatusID() == 1) {
                        loader.setVisibility(View.GONE);
                        Gobal.setGuiID(settingConfigRepose.getGUIID());
                        SettingConfig  settingConfig = new SettingConfig();
                        settingConfig.setConfigID(1);
                        settingConfig.setGuiID(settingConfigRepose.getGUIID());
                        settingConfig.setSiteID(edtSiteID.getText().toString());
                        settingConfig.setStoreID(edtSiteID.getText().toString());
                        settingConfig.setWebAPI("https://"+edtwebapi.getText().toString()+"/api/hr/");
                        if(!db.InsertSettingConfig(settingConfig)){
                            dialogcustom("Lưu Trữ Dữ Liệu Bị Lỗi");
                        }
                        else {

                            showLoading();
                            Toast.makeText(SettingConfigActivity.this, "Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SettingConfigActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        dialogcustom("Lỗi");
                    }
                    }
                }

            @Override
            public void onFailure(Call<SettingConfigRepose> call, Throwable t) {
                dialogcustom("Lỗi Kết Nối, Vui Lòng Thử Lại Sau");
            }
        });
    }
    public void dialogcustom(String text){
        final Dialog dialog = new Dialog(SettingConfigActivity.this);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
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
    public void showLoading() {
        findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.loader).setVisibility(View.GONE);
    }

}
