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
    TextView TxtVerificationCode, TxtSendNumber;
    EditText edOtpNumber1, edOtpNumber2, edOtpNumber3, edOtpNumber4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verify_otp);
        findViewBy_Id();
        Toast.makeText(this, "" + getIntent().getExtras().getString("code"), Toast.LENGTH_SHORT).show();
        TxtSendNumber.setText(TxtSendNumber.getText().toString() + " " + getIntent().getExtras().getString("mobile") + ")");

        edOtpNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edOtpNumber1.getText().toString().length() == 0) {
                    edOtpNumber1.requestFocus();
                }else VeryfyOtp();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edOtpNumber1.getText().toString().length() == 1){

                    edOtpNumber2.requestFocus();
                } else {
                    edOtpNumber1.requestFocus();
                }
            }
        });

        edOtpNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edOtpNumber2.getText().toString().length() == 0) {
                    edOtpNumber1.requestFocus();
                }else VeryfyOtp();

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edOtpNumber2.getText().toString().length() == 1)     //size as per your requirement
                {
                    edOtpNumber3.requestFocus();
                } else {
                    edOtpNumber1.requestFocus();
                }
            }
        });

        edOtpNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edOtpNumber3.getText().toString().length() == 2) {
                    edOtpNumber2.requestFocus();
                }else VeryfyOtp();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edOtpNumber3.getText().toString().length() == 1) {
                    edOtpNumber4.requestFocus();
                } else {
                    edOtpNumber2.requestFocus();
                }

            }
        });

        edOtpNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edOtpNumber4.getText().toString().length() == 0) {
                    edOtpNumber3.requestFocus();
                }
                if (edOtpNumber4.getText().toString().length() == 1) {
                    VeryfyOtp();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edOtpNumber4.getText().toString().length() == 1){

                    edOtpNumber4.requestFocus();
                } else {
                    edOtpNumber3.requestFocus();
                }

            }
        });
    }

    private void VeryfyOtp() {

        String enterOtpString = edOtpNumber1.getText().toString() + edOtpNumber2.getText().toString() + edOtpNumber3.getText().toString() + edOtpNumber4.getText().toString();

        if(enterOtpString.length()==4) {

            if (getIntent().getExtras().getString("code").equals(enterOtpString)) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("contact1", getIntent().getExtras().getString("mobile"));
                startActivity(intent);

            } else {
                Toast.makeText(this, "Otp Does Not Verify", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void findViewBy_Id() {
        TxtVerificationCode = findViewById(R.id.TxtVerificationCode);
        TxtSendNumber = findViewById(R.id.TxtSendNumber);
        edOtpNumber1 = findViewById(R.id.opt_num1);
        edOtpNumber2 = findViewById(R.id.opt_num2);
        edOtpNumber3 = findViewById(R.id.opt_num3);
        edOtpNumber4 = findViewById(R.id.opt_num4);
    }
}
