package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.tse.app.R;

public class RegistrationForm2 extends AppCompatActivity {
    Spinner spRegistrationSelectCategory,spRegistrationMeters;
    EditText edRegistrationAddress1,edRegistrationArea1,edRegistrationPincode1,edRegistrationAddress2,
            edRegistrationArea2,edRegistrationPincode2,edRegistrationContactNo1,edRegistrationContactNo2,
            edRegistrationSubCategory,edRegistrationQuantity,edRegistrationReferralCode;
    RadioButton rbRegistrationGeneral,rbRegistrationPrime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form2);

        Findviewby_Id();


    }

    private void Findviewby_Id() {
        spRegistrationSelectCategory = (Spinner) findViewById(R.id.spRegistrationSelectCategory);
        spRegistrationMeters = (Spinner) findViewById(R.id.spRegistrationMeters);
        rbRegistrationGeneral = (RadioButton) findViewById(R.id.rbRegistrationGeneral);
        rbRegistrationPrime = (RadioButton) findViewById(R.id.rbRegistrationPrime);
        edRegistrationAddress1 =(EditText) findViewById(R.id.edRegistrationAddress1);
        edRegistrationArea1 = (EditText) findViewById(R.id.edRegistrationArea1);
        edRegistrationPincode1 = (EditText) findViewById(R.id.edRegistrationPincode1);
        edRegistrationAddress2 = (EditText) findViewById(R.id.edRegistrationAddress2);
        edRegistrationArea2 = (EditText) findViewById(R.id.edRegistrationArea2);
        edRegistrationPincode2 = (EditText) findViewById(R.id.edRegistrationPincode2);
        edRegistrationContactNo1 = (EditText) findViewById(R.id.edRegistrationContactNo1);
        edRegistrationContactNo2 = (EditText) findViewById(R.id.edRegistrationContactNo2);
        edRegistrationSubCategory = (EditText) findViewById(R.id.edRegistrationSubCategory);
        edRegistrationQuantity = (EditText) findViewById(R.id.edRegistrationQuantity);
        edRegistrationReferralCode = (EditText) findViewById(R.id.edRegistrationReferralCode);
    }

    public void movelogin(View view) {
        if (Validation()){
            Intent intent = new Intent(RegistrationForm2.this,LoginActivity.class);
            startActivity(intent);
        }


    }
    public boolean Validation(){
        if (edRegistrationAddress1.getText().toString().length()==0){
            edRegistrationAddress1.setError("Please Enter Address 1");
            return false;
        }
        else if (edRegistrationArea1.getText().toString().length()==0){
            edRegistrationArea1.setError("Please Enter Area 1");
            return false;
        }
        else if (edRegistrationPincode1.getText().toString().length()==0){
            edRegistrationPincode1.setError("Please Enter Pincode 1");
            return false;
        }
        else if (edRegistrationAddress2.getText().toString().length()==0){
            edRegistrationAddress2.setError("Please Enter Address 2");
            return false;
        }
        else if (edRegistrationArea2.getText().toString().length()==0){
            edRegistrationArea2.setError("Please Enter Area 2");
            return false;
        }
        else if (edRegistrationPincode2.getText().toString().length()==0){
            edRegistrationPincode2.setError("Please Enter Pincode 2");
            return false;
        }
        else if (edRegistrationAddress1.getText().toString().length()==0){
            edRegistrationAddress1.setError("Please Enter Address 1");
            return false;
        }
        else if (edRegistrationContactNo1.getText().toString().length()==0){
            edRegistrationContactNo1.setError("Please Enter Contact No. 1");
            return false;
        }
        else if (edRegistrationContactNo2.getText().toString().length()==0){
            edRegistrationContactNo2.setError("Please Enter  Contact No. 2");
            return false;
        }
        else if (edRegistrationSubCategory.getText().toString().length()==0){
            edRegistrationSubCategory.setError("Please Enter Sub category");
            return false;
        }
        else if (edRegistrationQuantity.getText().toString().length()==0){
            edRegistrationQuantity.setError("Please Enter Quantity");
            return false;
        } else if (edRegistrationReferralCode.getText().toString().length()==0){
            edRegistrationReferralCode.setError("Please Enter Referral Code");
            return false;
        }
        return true;

    }
}
