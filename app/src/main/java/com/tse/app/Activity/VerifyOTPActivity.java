package com.tse.app.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tse.app.R;

public class VerifyOTPActivity extends AppCompatActivity {
    TextView TxtVerificationCode,TxtSendNumber;
    EditText OptNumber1,OptNumber2,OptNumber3,OptNumber4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verify_otp);
        findViewBy_Id();
        Toast.makeText(this,""+getIntent().getExtras().getString("mobile"), Toast.LENGTH_SHORT).show();
        OptNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (OptNumber1.getText().toString().length() == 0) {
                    OptNumber1.requestFocus();
                }




            }

            @Override
            public void afterTextChanged(Editable s) {
                if (OptNumber1.getText().toString().length() == 1)     //size as per your requirement
                {
                    OptNumber2.requestFocus();
                }
                else {
                    OptNumber1.requestFocus();
                }


            }
        });

        OptNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (OptNumber2.getText().toString().length() == 0) {
                    OptNumber1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (OptNumber2.getText().toString().length() == 1)     //size as per your requirement
                {
                    OptNumber3.requestFocus();
                }else {
                    OptNumber1.requestFocus();
                }


            }
        });

        OptNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (OptNumber3.getText().toString().length() == 2) {
                    OptNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (OptNumber3.getText().toString().length() == 1)     //size as per your requirement
                {
                    OptNumber4.requestFocus();
                }
                else {
                    OptNumber2.requestFocus();
                }

            }
        });

        OptNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (OptNumber4.getText().toString().length() == 0) {
                    OptNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (OptNumber4.getText().toString().length() == 1)     //size as per your requirement
                {
                    OptNumber4.requestFocus();
                }else {
                    OptNumber3.requestFocus();
                }

            }

        });







    }

    private void findViewBy_Id() {
        TxtVerificationCode = findViewById(R.id.TxtVerificationCode);
        TxtSendNumber = findViewById(R.id.TxtSendNumber);
        OptNumber1 = findViewById(R.id.opt_num1);
        OptNumber2 = findViewById(R.id.opt_num2);
        OptNumber3 = findViewById(R.id.opt_num3);
        OptNumber4 = findViewById(R.id.opt_num4);

    }

    public void register(View view) {
        Intent intent = new Intent(VerifyOTPActivity.this,RegistrationForm1.class);
        startActivity(intent);

    }

}
