package com.example.myapplication.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.R;
import com.example.myapplication.Request.ChangePasswordRequest;
import com.example.myapplication.Response.ChangePasswordResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    DBHelper db;
    Button btnThoat;
    Button aceptchangepasssword;
    ImageView backchangepass;
    TextInputEditText tip_edt_passwordnewxn, tip_edt_passwordnew, tip_edt_password;
    TextInputLayout tiplo_pass, tiplo_passnew, tiplo_passnewxn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_changepassword);
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        btnThoat = findViewById(R.id.btnClose);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        backchangepass = findViewById(R.id.backchangepass);
        backchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tip_edt_password = findViewById(R.id.tip_edt_password);
        tip_edt_passwordnew = findViewById(R.id.tip_edt_passwordnew);
        tip_edt_passwordnewxn = findViewById(R.id.tip_edt_passwordnewxn);
        tip_edt_password.addTextChangedListener(new MytextWatcher(tip_edt_password));
        tip_edt_passwordnew.addTextChangedListener(new MytextWatcher(tip_edt_passwordnew));
        tip_edt_passwordnewxn.addTextChangedListener(new MytextWatcher(tip_edt_passwordnewxn));
        tiplo_passnewxn = findViewById(R.id.tiplo_passnewxn);
        tiplo_pass = findViewById(R.id.tiplo_pass);
        tiplo_passnew = findViewById(R.id.tiplo_passnew);
        aceptchangepasssword = findViewById(R.id.aceptchangepasssword);
        aceptchangepasssword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (!validatePassword() == false || !validatePasswordnew() == false || !validatePasswordnewxn() == false) {
                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
                    changePasswordRequest.setUserID(db.GetUser().getUserID());
                    changePasswordRequest.setCurrentPassword(tiplo_pass.getEditText().getText().toString());
                    changePasswordRequest.setNewPassword(tiplo_passnew.getEditText().getText().toString());
                    if (tiplo_pass.getEditText().getText().toString().equals(tiplo_passnew.getEditText().getText().toString())){
                        Toast.makeText(getApplication(), "Mật khẩu mới không được trùng với mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    }else if (!tiplo_passnew.getEditText().getText().toString().equals(tiplo_passnewxn.getEditText().getText().toString())){
                        Toast.makeText(getApplication(), "Xác nhận mật khẩu khôp khớp", Toast.LENGTH_SHORT).show();
                    }else {
                        changePassword(changePasswordRequest);
                    }
                }
            }
        });
    }

    public boolean validatePassword() {
        String passwordinput = tiplo_pass.getEditText().getText().toString().trim();
        if (passwordinput.isEmpty()) {
            tiplo_pass.setError("Item này không được rỗng");
            return false;
        } else
            tiplo_pass.setError(null);
        return true;
    }
    public boolean validatePasswordnew() {
        String  passwordinputnew = tiplo_passnew.getEditText().getText().toString().trim();
        if (passwordinputnew.isEmpty()) {
            tiplo_passnew.setError("Item này không được rỗng");
            return false;
        } else
            tiplo_passnew.setError(null);
        return true;
    }
    public boolean validatePasswordnewxn(){
        String passwordinputpassxn = tiplo_passnewxn.getEditText().getText().toString().trim();
        if (passwordinputpassxn.isEmpty()) {
            tiplo_passnewxn.setError("Item này không được rỗng");
            return false;
        }else
            tiplo_passnewxn.setError(null);
        return true;
    }
    public class MytextWatcher implements TextWatcher {
        private View view;

        private MytextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.tip_edt_password:
                    validatePassword();
                    break;
                case R.id.tip_edt_passwordnew:
                    validatePasswordnew();
                    break;
                case R.id.tip_edt_passwordnewxn:
                    validatePasswordnewxn();
                    break;
            }
        }
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        Call<ChangePasswordResponse> changePasswordResponseCall = APIClient.changePasswordSevice().ChangePassword(Gobal.getGuiID(), changePasswordRequest);
        changePasswordResponseCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful()) {
                    ChangePasswordResponse changePasswordResponse = response.body();
                    if (changePasswordResponse.getStatusID() == 1) {
                        Toast.makeText(getApplication(), changePasswordResponse.getErrorDescription() + "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                        db.DeleteUser(Gobal.getUser().getUserID());
                        Intent intent = new Intent(getApplication(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(), changePasswordResponse.getErrorDescription(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

            }
        });
    }

}