package com.tse.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tse.app.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
    }
}
