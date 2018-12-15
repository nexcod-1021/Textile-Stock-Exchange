package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tse.app.R;

public class RegistrationForm1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form1);
    }

    public void MoveRegistrationform2(View view) {
        Intent intent = new Intent(RegistrationForm1.this,RegistrationForm2.class);
        startActivity(intent);

    }
}
