package com.example.myapplication.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0015¨\u0006\u0007"},
        d2 = {"Lcom/example/myapplication/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}
)
public class InfomationWifi extends AppCompatActivity {
    private HashMap _$_findViewCache;

    @SuppressLint({"SetTextI18n", "NewApi"})
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.info);
        operateContactWrapper();
        @SuppressLint("WrongConstant") Object var10000 = this.getApplicationContext().getSystemService("wifi");
        if (var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        } else {
            WifiManager wifiManager = (WifiManager) var10000;
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            Intrinsics.checkExpressionValueIsNotNull(wInfo, "wInfo");
            String ipAddress = Formatter.formatIpAddress(wInfo.getIpAddress());
            int linkSpeed = wInfo.getLinkSpeed();
            int networkID = wInfo.getNetworkId();
            String ssid = wInfo.getSSID();
            boolean hssid = wInfo.getHiddenSSID();
            String bssid = wInfo.getBSSID();
            TextView wifiInformationTv = (TextView) this.findViewById(R.id.wifiInfo);
            Intrinsics.checkExpressionValueIsNotNull(wifiInformationTv, "wifiInformationTv");
            wifiInformationTv.setText((CharSequence) ("IP Address:\t" + ipAddress + '\n' + "Link Speed:\t" + linkSpeed + '\n' + "Network ID:\t" + networkID + '\n' + "SSID:\t" + ssid + '\n' + "Hidden SSID:\t" + hssid + '\n' + "BSSID:\t" + bssid + '\n'));
        }
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View) this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void operateContactWrapper() {
        int hasWriteContactsPermission = checkSelfPermission(ACCESS_FINE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_location);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button btn = dialog.findViewById(R.id.acceptlocation);
                dialog.show();
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        operateContactWrapper();
                        dialog.dismiss();
                    }
                });

            }
        } else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        @SuppressLint("WrongConstant") Object var10000 = this.getApplicationContext().getSystemService("wifi");
        if (var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        } else {
            WifiManager wifiManager = (WifiManager) var10000;
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            Intrinsics.checkExpressionValueIsNotNull(wInfo, "wInfo");
            String ipAddress = Formatter.formatIpAddress(wInfo.getIpAddress());
            int linkSpeed = wInfo.getLinkSpeed();
            int networkID = wInfo.getNetworkId();
            String ssid = wInfo.getSSID();
            boolean hssid = wInfo.getHiddenSSID();
            String bssid = wInfo.getBSSID();
            TextView wifiInformationTv = (TextView) this.findViewById(R.id.wifiInfo);
            Intrinsics.checkExpressionValueIsNotNull(wifiInformationTv, "wifiInformationTv");
            wifiInformationTv.setText((CharSequence) ("IP Address:\t" + ipAddress + '\n' + "Link Speed:\t" + linkSpeed + '\n' + "Network ID:\t" + networkID + '\n' + "SSID:\t" + ssid + '\n' + "Hidden SSID:\t" + hssid + '\n' + "BSSID:\t" + bssid + '\n'));
        }
    }
}
