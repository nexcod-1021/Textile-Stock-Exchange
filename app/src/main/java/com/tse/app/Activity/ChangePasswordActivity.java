package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tse.app.R;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edCurrentPassword, edNewPassword, edConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findViewBy_Id();

    }

    private void findViewBy_Id() {
        edCurrentPassword = (EditText) findViewById(R.id.edCurrentPasswor);
        edNewPassword = (EditText) findViewById(R.id.edNewPasswor);
        edConfirmPassword = (EditText) findViewById(R.id.edConfirmPasswor);

    }

    public void MovetoLoginActivity(View view) {
        if (Validation()) {
            Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public boolean Validation() {
        if (edCurrentPassword.getText().toString().length() == 0) {
            edCurrentPassword.setError("Please Enter Current Password");
            return false;
        } else if (edNewPassword.getText().toString().length() == 0) {
            edNewPassword.setError("Please Enter New Password");
            return false;
        } else if (edConfirmPassword.getText().toString().length() == 0) {
            edConfirmPassword.setError("Please Enter Confirm Password");
            return false;

        } else if (!edNewPassword.getText().toString().equals(edConfirmPassword.getText().toString())) {
            edConfirmPassword.setError("password  not are match");
            return false;

        }


        return true;
    }
}
