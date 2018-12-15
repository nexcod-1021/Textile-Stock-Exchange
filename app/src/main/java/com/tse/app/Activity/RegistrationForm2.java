package com.tse.app.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tse.app.R;

public class RegistrationForm2 extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form2);
        spinner = findViewById(R.id.generalSpinner);


    }

    public void movelogin(View view) {
        Intent intent = new Intent(RegistrationForm2.this,LoginActivity.class);
        startActivity(intent);

    }
}
