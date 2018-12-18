package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tse.app.R;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edCurrentPasswor,edNewPasswor,edConfirmPasswor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findViewBy_Id();

    }

    private void findViewBy_Id() {
        edCurrentPasswor = (EditText) findViewById(R.id.edCurrentPasswor);
        edNewPasswor = (EditText) findViewById(R.id.edNewPasswor);
        edConfirmPasswor = (EditText) findViewById(R.id.edConfirmPasswor);

    }

    public void MovetoLoginActivity(View view) {
        if (Validation()){
             if(edNewPasswor.getText().toString().equals(edConfirmPasswor.getText().toString())) {
                 Intent intent = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                 startActivity(intent);
             }else {
                 edConfirmPasswor.setError("password  not are match");
             }


        }



    }

    public boolean Validation() {
        if (edCurrentPasswor.getText().toString().length()==0){
            edCurrentPasswor.setError("Please Enter Current Password");
            return false;
        }
       else if (edNewPasswor.getText().toString().length()==0){
            edNewPasswor.setError("Please Enter New Password");
            return false;
        }
        else if (edConfirmPasswor.getText().toString().length()==0){
            edConfirmPasswor.setError("Please Enter Confirm Password");
            return false;

        }else {

            return true;

        }




    }
}
