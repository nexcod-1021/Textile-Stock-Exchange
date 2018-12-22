package com.tse.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.tse.app.Config;
import com.tse.app.MyService;
import com.tse.app.R;

public class SplashScreanActivity extends AppCompatActivity {
    Handler handler;
    SharedPreferences sharedPreferencesRemember;
    private static final int REQUEST_WRITE_PERMISSION = 786;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screan);
        startService(new Intent(getBaseContext(), MyService.class));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        } else {
            sharedPreferencesRemember = PreferenceManager.getDefaultSharedPreferences(SplashScreanActivity.this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    moveIntent();

                }
            }, SPLASH_TIME_OUT);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sharedPreferencesRemember = PreferenceManager.getDefaultSharedPreferences(SplashScreanActivity.this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    moveIntent();
                }
            }, SPLASH_TIME_OUT);
        } else {
            requestPermission();
        }
    }

    private void moveIntent() {
        if (sharedPreferencesRemember.getString(Config.spPriceGeneral, "").equalsIgnoreCase("")) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
        }
    }


}
