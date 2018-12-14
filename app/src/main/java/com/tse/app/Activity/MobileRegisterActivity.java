package com.tse.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.tse.app.R;

public class MobileRegisterActivity extends AppCompatActivity {
    EditText edMobileNumber;
    Button btnMobileRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_register);
        findViewBy_Id();


    }

    private void findViewBy_Id() {
        edMobileNumber = findViewById(R.id.edMobileNumber);
        btnMobileRegister = findViewById(R.id.btnMobileRegister);


    }

    public void submit(View view) {
        Intent intent = new Intent(MobileRegisterActivity.this,VerifyOTPActivity.class);
        startActivity(intent);

    }
}
