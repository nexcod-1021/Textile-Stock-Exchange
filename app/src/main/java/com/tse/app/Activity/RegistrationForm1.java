package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.tse.app.R;

public class RegistrationForm1 extends AppCompatActivity {

    RadioButton rbRegisterManufature, rbRegisterTraders, rbRegisterService, rbRegisterBroker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form1);
        findViewByIdS();


        rbRegisterManufature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbRegisterManufature.setChecked(true);
                rbRegisterTraders.setChecked(false);
                rbRegisterService.setChecked(false);
                rbRegisterBroker.setChecked(false);

            }
        });
        rbRegisterTraders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbRegisterManufature.setChecked(false);
                rbRegisterTraders.setChecked(true);
                rbRegisterService.setChecked(false);
                rbRegisterBroker.setChecked(false);

            }
        });
        rbRegisterService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbRegisterManufature.setChecked(false);
                rbRegisterTraders.setChecked(false);
                rbRegisterService.setChecked(true);
                rbRegisterBroker.setChecked(false);
            }
        });
        rbRegisterBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbRegisterManufature.setChecked(false);
                rbRegisterTraders.setChecked(false);
                rbRegisterService.setChecked(false);
                rbRegisterBroker.setChecked(true);
            }
        });


    }

    private void findViewByIdS() {
        rbRegisterManufature = (RadioButton) findViewById(R.id.rb_register_manufacture);
        rbRegisterTraders = (RadioButton) findViewById(R.id.rb_register_traders);
        rbRegisterService = (RadioButton) findViewById(R.id.rb_register_service);
        rbRegisterBroker = (RadioButton) findViewById(R.id.rb_register_broker);
    }



    public void MoveRegistrationform2(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrationForm2.class);
        startActivity(intent);
    }
}
