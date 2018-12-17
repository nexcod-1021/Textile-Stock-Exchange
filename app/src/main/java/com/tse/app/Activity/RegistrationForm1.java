package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.tse.app.R;

public class RegistrationForm1 extends AppCompatActivity {

    RadioButton rbRegisterManufature, rbRegisterTraders, rbRegisterService, rbRegisterBroker;
    EditText edRegistrationfirstName,edRegistrationLastName, edRegistrationEmail, edRegistrationPassword, edRegistrationCnfirmPassword, edRegistrationcEnterprice, edRegistrationGstNo, edRegistrationPanNo;


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
        edRegistrationfirstName = (EditText) findViewById(R.id.edRegistrationfirstName);
        edRegistrationLastName = (EditText) findViewById(R.id.edRegistrationLastName);
        edRegistrationEmail = (EditText) findViewById(R.id.edRegistrationEmail);
        edRegistrationPassword = (EditText) findViewById(R.id.edRegistrationPassword);
        edRegistrationCnfirmPassword = (EditText) findViewById(R.id.edRegistrationCnfirmPassword);
        edRegistrationcEnterprice = (EditText) findViewById(R.id.edRegistrationcEnterprice);
        edRegistrationGstNo = (EditText) findViewById(R.id.edRegistrationGstNo);
        edRegistrationPanNo = (EditText) findViewById(R.id.edRegistrationPanNo);

    }


    public void MoveRegistrationform2(View view) {
        if (validation()) {
            Intent intent = new Intent(getApplicationContext(), RegistrationForm2.class);
            startActivity(intent);
       }


    }

    public boolean validation() {
        if (edRegistrationfirstName.getText().toString().length() == 0) {
            edRegistrationfirstName.setError("Please enter First Name");
            return false;
        } else if (edRegistrationLastName.getText().toString().length() == 0) {
            edRegistrationLastName.setError("Please enter Last Name");
            return false;
        }

        else if (edRegistrationEmail.getText().toString().length() == 0) {
            edRegistrationEmail.setError("Please enter Email");
            return false;
        } else if (!edRegistrationEmail.getText().toString().matches("^[a-zA-Z0-9_\\+-]+(\\.[a-zA-Z0-9_\\+-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,4})$")) {
            edRegistrationEmail.setError("email not match");
            return false;


        } else if (edRegistrationPassword.getText().toString().length() == 0) {
            edRegistrationPassword.setError("Please enter Password");
            return false;
        } else if (edRegistrationCnfirmPassword.getText().toString().length() == 0) {
            edRegistrationCnfirmPassword.setError("Please enter Password");
            return false;

        } else if (edRegistrationcEnterprice.getText().toString().length() == 0) {
            edRegistrationcEnterprice.setError("Please enter EnterPrice");
            return false;
        } else if (edRegistrationGstNo.getText().toString().length() == 0) {
            edRegistrationGstNo.setError("Please enter GST NO.");
            return false;

        } else if (edRegistrationPanNo.getText().toString().length() == 0) {
            edRegistrationPanNo.setError("Please enter PAN NO.");
            return false;

        }
        return true;
    }

}
