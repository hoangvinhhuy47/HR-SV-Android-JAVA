package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.Model.DataGetProfile;
import com.example.myapplication.R;
import com.example.myapplication.Request.GetProfileRequest;
import com.example.myapplication.Request.UpdateProfileRequest;
import com.example.myapplication.Response.GetProfileResponse;
import com.example.myapplication.Response.UpdateProfileResponse;
import com.example.myapplication.Utils.GobalUtils;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    Button backuser, acceptuser;
    EditText username, emailuser, dateborn, phone, andress, number_cc, MaritalStatus, fullname;
    ImageView backleave;
    ImageView img_user;
    DBHelper db;
    DataGetProfile dataGetProfile = new DataGetProfile();
    String dateupdate, dateBornupdate;
    RadioButton madle_man, madle_women;
    int genderupdate;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_user);
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        acceptuser = findViewById(R.id.acceptuser);
        backleave = findViewById(R.id.backleave);
        username = findViewById(R.id.username);
        emailuser = findViewById(R.id.emailuser);
        dateborn = findViewById(R.id.dateborn);
        phone = findViewById(R.id.phone);
        madle_man = findViewById(R.id.madle_man);
        madle_women = findViewById(R.id.madle_women);
        number_cc = findViewById(R.id.number_cc);
        MaritalStatus = findViewById(R.id.MaritalStatus);
        emailuser.setFocusable(false);
        username.setFocusable(false);
        backleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        madle_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                madle_man.setChecked(true);
                madle_women.setChecked(false);
                genderupdate = 1;
            }
        });
        madle_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                madle_man.setChecked(false);
                madle_women.setChecked(true);
                genderupdate = 0;
            }
        });
        andress = findViewById(R.id.andress);
        //  dateincompany = findViewById(R.id.dateincompany);
//        dateincompany.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar mcurrentDate = Calendar.getInstance();
//                int mYear = mcurrentDate.get(Calendar.YEAR);
//                int mMonth = mcurrentDate.get(Calendar.MONTH);
//                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog mDatePicker;
//                mDatePicker = new DatePickerDialog(AccountActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                        // TODO Auto-generated method stub
//                        /*      Your code   to get date and time    */
//                        selectedmonth = selectedmonth + 1;
//                        if (selectedday < 9 && selectedmonth < 9) {
//                            dateincompany.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
//                            dateupdate=("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
//                        } else if (selectedday < 9 && selectedmonth > 9) {
//                            dateincompany.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
//                            dateupdate=("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
//                        } else if (selectedday > 9 && selectedmonth > 9) {
//                            dateincompany.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
//                            dateupdate=("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
//                        } else if (selectedday > 9 && selectedmonth < 9) {
//                            dateincompany.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
//                            dateupdate=("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
//                        }
//                    }
//                }, mYear, mMonth, mDay);
//                mDatePicker.setTitle("Select Date");
//                mDatePicker.show();
//            }
//        });
        dateborn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        if (selectedday < 9 && selectedmonth < 9) {
                            dateborn.setText("" + "0" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            dateBornupdate = ("" + selectedyear + "/" + "0" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday < 9 && selectedmonth > 9) {
                            dateborn.setText("" + "0" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            dateBornupdate = ("" + selectedyear + "/" + selectedmonth + "/" + "0" + selectedday);
                        } else if (selectedday > 9 && selectedmonth > 9) {
                            dateborn.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                            dateupdate = ("" + selectedyear + "/" + selectedmonth + "/" + selectedday);
                        } else if (selectedday > 9 && selectedmonth < 9) {
                            dateborn.setText("" + selectedday + "/" + "0" + selectedmonth + "/" + selectedyear);
                            dateBornupdate = ("" + selectedyear + "/" + "0" + selectedmonth + "/" + selectedday);
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
//        number = findViewById(R.id.number);
//        relationship = findViewById(R.id.relationship);
        backuser = findViewById(R.id.backuser);
        img_user = findViewById(R.id.img_user);
        fullname = findViewById(R.id.fullname);
        GetProfileRequest getProfileRequest = new GetProfileRequest();
        getProfileRequest.setUserID(db.GetUser().getUserID());
        getProfile(getProfileRequest);
        backuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void getProfile(GetProfileRequest getProfileRequest) {
        Call<GetProfileResponse> getProfileResponseCall = APIClient.getProfileService().Data(Gobal.getGuiID(), getProfileRequest);
        getProfileResponseCall.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful()) {
                    GetProfileResponse getProfileResponse = response.body();
                    if (getProfileResponse.getStatusID() == 1) {
                        if (getProfileResponse.getData().isChangedPass() == true) {
                            dataGetProfile = getProfileResponse.getData();
//                            if (getProfileResponse.getData().getAvatar() != null) {
////                                Picasso.with(getApplication())
////                                        .load(Gobal.IDImage + getProfileResponse.getData().getAvatar())
////                                        .fit().centerCrop()
////                                        .into(img_user);
//                            }else {
                            Picasso.with(getApplication())
                                    .load("http://hr.softwareviet.com/Content/images/default-avatar.jpg")
                                    .fit().centerCrop()
                                    .into(img_user);
//                            }
                            username.setText(getProfileResponse.getData().getUserName());
                            fullname.setText(getProfileResponse.getData().getFullName());
                            emailuser.setText(getProfileResponse.getData().getEmailAddress());
                            dateborn.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(getProfileResponse.getData().getBirthDay())));
                            phone.setText(getProfileResponse.getData().getCellPhone());
                            andress.setText(getProfileResponse.getData().getAddress());
                            number_cc.setText(getProfileResponse.getData().getIdentityCard());
                            MaritalStatus.setText(GobalUtils.MaritalStatus(getProfileResponse.getData().getMaritalStatus()));
                            if (getProfileResponse.getData().getGender() == 0) {
                                madle_women.setChecked(true);
                                madle_man.setChecked(false);
                                genderupdate = 0;
                            } else {
                                madle_women.setChecked(false);
                                madle_man.setChecked(true);
                                genderupdate = 1;
                            }
                            dateupdate = getProfileResponse.getData().getCreatedDate();
                            dateBornupdate = getProfileResponse.getData().getBirthDay();
//                            dateincompany.setText(GobalUtils.convertDateShowScreen(GobalUtils.convertStringToDate(getProfileResponse.getData().getCreatedDate())));
                            acceptuser.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
                                    dataGetProfile.setFullName(fullname.getText().toString());
                                    dataGetProfile.setBirthDay(dateBornupdate);
                                    dataGetProfile.setCellPhone(phone.getText().toString());
                                    dataGetProfile.setGender(genderupdate);
                                    dataGetProfile.setAddress(andress.getText().toString());
                                    dataGetProfile.setAvatar("http://hr.softwareviet.com/Content/images/default-avatar.jpg");
                                    updateProfileRequest.setUserProfile(dataGetProfile);
                                    UpdateProfile(updateProfileRequest);
                                }
                            });
                        } else {
                            Intent intent = new Intent(getApplication(), ChangePasswordActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Toast.makeText(AccountActivity.this, "Bạn hãy đổi mật khẩu", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void UpdateProfile(UpdateProfileRequest updateProfileRequest) {
        Call<UpdateProfileResponse> updateProfileResponseCall = APIClient.updateProfileService().UpdateProfile(Gobal.getGuiID(), updateProfileRequest);
        updateProfileResponseCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful()) {
                    UpdateProfileResponse updateProfileResponse = response.body();
                    if (updateProfileResponse.getStatusID() == 1) {
                        Toast.makeText(getApplicationContext(), "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "" + updateProfileResponse.getErrorDescription(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}