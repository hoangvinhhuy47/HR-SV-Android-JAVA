package com.example.myapplication.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Database.DBHelper;

import com.example.myapplication.Fragment.HomeActivity;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataGetProfile;
import com.example.myapplication.Model.User;
import com.example.myapplication.R;
import com.example.myapplication.Request.GetProfileRequest;
import com.example.myapplication.Request.LoadBeginCheckInRequest;
import com.example.myapplication.Response.GetProfileResponse;
import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Response.UserReponse;
import com.example.myapplication.Request.LoginRequest;
import com.example.myapplication.Utils.GobalUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    LoginActivity extends AppCompatActivity {
    Button btnlogin;
    Button btnClose;
    TextInputEditText edtid;
    TextInputEditText edtpass;
    DBHelper db;
    ImageView imgexit;
    Button btnext;
    TextView ttvTitle;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);
        btnlogin = findViewById(R.id.btnLogin);
        edtid = findViewById(R.id.iduser);
        edtpass = findViewById(R.id.idpassword);
        btnClose = findViewById(R.id.btnthoat);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.DeleteConfig(1);
                Intent intent = new Intent(getApplication(),SettingConfigActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        User user = db.GetUser();
        if (user != null) {
            if(user.getUserID()!=null )
            {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUserName(user.getUserName());
                loginRequest.setPassword(user.getPassword());
                login(loginRequest,true);
            }
        }
        else {
            dialogcustom("Load dữ liệu người dùng không thành công");
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUserName(edtid.getText().toString());
                loginRequest.setPassword(edtpass.getText().toString());
                login(loginRequest, false);

            }
        });
    }
    public void login(LoginRequest loginRequest,final boolean isExistUser) {
        Call<UserReponse> loginResponseCall = APIClient.getUserService().CheckLogin(Gobal.getGuiID(),loginRequest);
        loginResponseCall.enqueue(new Callback<UserReponse>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<UserReponse> call, Response<UserReponse> response) {
                if (response.isSuccessful()) {
                    UserReponse userReponse = response.body();
                    if (userReponse.getStatusID() == 1) {
                        User user = userReponse.getUser();
                        // Set value to Variable Gobal
                        GetProfileRequest getProfileRequest =new GetProfileRequest();
                        getProfileRequest.setUserID(user.getUserID());
                        getProfile(getProfileRequest,user,isExistUser);

                    } else {
                        dialogcustom("Không thể đăng nhập");
                    }
                }
            }
            @Override
            public void onFailure(Call<UserReponse> call, Throwable t) {
                dialogcustom("Không thể kết nối internet!");
            }
        });
    }
    public void getProfile(GetProfileRequest getProfileRequest, User user,final boolean isExistUser) {
        Call<GetProfileResponse> getProfileResponseCall = APIClient.getProfileService().Data(Gobal.getGuiID(), getProfileRequest);
        getProfileResponseCall.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful()) {
                    GetProfileResponse getProfileResponse = response.body();
                    if (getProfileResponse.getStatusID() == 1) {
                            if (getProfileResponse.getData().isChangedPass()==true){
                                Gobal.setUser(user);
                                Gobal.setUserID(user.getUserID());
                                if(!isExistUser)
                                {
                                    // Insert User to SQLLite
                                    if(!db.InsertUser(user)) {
                                        dialogcustom("Lưu Trữ Dữ Liệu Không Thành Công");
                                    }
                                    else {
                                        showLoading();
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivityForResult(intent, 300);
                                    }
                                }

                                else {
                                    showLoading();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivityForResult(intent, 300);

                                }

                            }
                        else {
                            Intent intent = new Intent(getApplication(),ChangePasswordActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });
    }
    public void showLoading() {
        findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.loader).setVisibility(View.GONE);
    }
        public void dialogcustom(String text){
            final Dialog dialog = new Dialog(LoginActivity.this);
            dialog.setContentView(R.layout.alert_dialog);
            dialog.show();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_Design_BottomSheetDialog;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            imgexit = dialog.findViewById(R.id.exit);
            btnext = dialog.findViewById(R.id.accept);
            ttvTitle=dialog.findViewById(R.id.ttvTitle);
            ttvTitle.setText(text);

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


