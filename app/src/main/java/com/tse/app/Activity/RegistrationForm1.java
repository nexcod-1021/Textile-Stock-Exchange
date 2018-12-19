package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tse.app.R;

public class RegistrationForm1 extends AppCompatActivity {

    RadioButton rbRegisterManufature, rbRegisterTraders, rbRegisterService, rbRegisterBroker;
    EditText edRegistrationfirstName, edRegistrationLastName, edRegistrationEmail, edRegistrationPassword, edRegistrationCnfirmPassword, edRegistrationcEnterprice, edRegistrationGstNo, edRegistrationPanNo;
    ImageView ivProfileImage;

    ProgressDialog dialog;
    private int PICK_PDF_REQUEST = 1;
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    String selectedFilePath;

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

        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
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
        ivProfileImage = (ImageView) findViewById(R.id.iv_register_profile_pic);
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
        } else if (edRegistrationEmail.getText().toString().length() == 0) {
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

        } else if (!edRegistrationPassword.getText().toString().equals(edRegistrationCnfirmPassword.getText().toString())) {
            edRegistrationCnfirmPassword.setError("password  not are match");
            return false;
        }
        return true;
    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose File"), PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedFileUri = data.getData();
            selectedFilePath = FilePath.getPath(this, selectedFileUri);
            Glide.with(getApplicationContext()).load(selectedFilePath).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(ivProfileImage);
            Log.i(TAG, "Selected File Path:" + selectedFilePath);
        }
    }
}
