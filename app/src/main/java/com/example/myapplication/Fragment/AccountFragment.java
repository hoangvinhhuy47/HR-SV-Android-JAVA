package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.ChangePasswordActivity;
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.Activity.AccountActivity;
import com.example.myapplication.Activity.SettingConfigActivity;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.R;


public class AccountFragment extends Fragment {
    RelativeLayout imformationuser,changepassword,settingcofig;
    Button signout,signoutuser;
    DBHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        db = new DBHelper(getActivity());
        imformationuser = (RelativeLayout) view.findViewById(R.id.imformationuser);
        imformationuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
            }
        });
        changepassword = (RelativeLayout) view.findViewById(R.id.changepassword);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        signout=(Button) view.findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);

        }
        });
        settingcofig=(RelativeLayout) view.findViewById(R.id.settingcofig);
        settingcofig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteConfig(1);
                db.DeleteUser(Gobal.getUser().getUserID());
                Intent intent = new Intent(getActivity(),SettingConfigActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        signoutuser=(Button) view.findViewById(R.id.signoutuser);
        signoutuser.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                db.DeleteUser(Gobal.getUser().getUserID());
                db.GetConfig();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return view;

    }
} 
