
package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.Activity.LoginActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Gobal.Gobal;
import com.example.myapplication.R;
import com.example.myapplication.Request.LoadBeginCheckInRequest;
import com.example.myapplication.Response.LoadBeginCheckInResponse;
import com.example.myapplication.Utils.InfomationWifi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    BottomNavigationView navView;
    TextView username;
    ImageView imgexit;
    Button btnexit;
    TextView ttvTitle;
    ImageView loaddingwifi;
    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    @SuppressLint("NewApi")
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loaddingwifi = findViewById(R.id.loaddingwifi);
        Fragment id;
        id = new HomeFragment();
        loadFragment(id);
        operateContactWrapper();
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
//        bottomNavigationView.getMenu().findItem(R.id.Home).setChecked(true);
        bottomNavigationView.setSelectedItemId(R.id.Home);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
         username = findViewById(R.id.username);
        username.setText(Gobal.getUser().getFullName());
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(HomeActivity.this)
                    .addOnConnectionFailedListener(HomeActivity.this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        loaddingwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();

//                Intent intent = new Intent(getApplication(), InfomationWifi.class);
//                startActivity(intent);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment id ;
                    switch (item.getItemId()) {
                        case R.id.Home:
                            id = new HomeFragment();
                            loadFragment(id);
                            return true;
                        case R.id.task:
                            id = new TaskFragment();
                            loadFragment(id);
                            return true;
                        case R.id.equitment:
                            id = new EquitmentFragment();
                            loadFragment(id);
                            break;
                        case R.id.account:
                            id = new AccountFragment();
                            loadFragment(id);
                            break;
                    }
                    return true;
                }
            };


    //chuyen doi layout
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void getMyLocation() {
        try {
            /* code should explicitly check to see if permission is available
            (with 'checkPermission') or explicitly handle a potential 'SecurityException'
             */
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
//               Toast.makeText(getApplication(),""+String.valueOf(mLastLocation.getLatitude()) + "\n" + String.valueOf(mLastLocation.getLongitude()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeActivity.this,
//                        String.valueOf(mLastLocation.getLatitude()) + "\n"
//                                + String.valueOf(mLastLocation.getLongitude()),
//                        Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(HomeActivity.this,
//                        "",
//                        Toast.LENGTH_LONG).show();
            }
        } catch (SecurityException e) {
//            Toast.makeText(HomeActivity.this,
//                    "SecurityException:\n" + e.toString(),
//                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getMyLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
//        Toast.makeText(HomeActivity.this,
//                "onConnectionSuspended: " + String.valueOf(i),
//                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Toast.makeText(HomeActivity.this,
//                "onConnectionFailed: \n" + connectionResult.toString(),
//                Toast.LENGTH_LONG).show();
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
                operateContactWrapper();
            } else {
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_location);
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
    }
}

