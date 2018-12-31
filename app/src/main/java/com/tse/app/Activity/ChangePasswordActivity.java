package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Config;
import com.tse.app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edCurrentPassword, edNewPassword, edConfirmPassword;
    ProgressDialog pg;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findViewBy_Id();
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ChangePasswordActivity.this);
       editor = sharedPreferences.edit();
    }

    private void findViewBy_Id() {
        edCurrentPassword = (EditText) findViewById(R.id.edCurrentPasswor);
        edNewPassword = (EditText) findViewById(R.id.edNewPasswor);
        edConfirmPassword = (EditText) findViewById(R.id.edConfirmPasswor);

    }

    public void MovetoLoginActivity(View view) {
        if (Validation()) {
            new ChangePassword().execute();
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
    private class ChangePassword extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(ChangePasswordActivity.this);
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.UrlExistMobile,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject j = new JSONObject(response.trim());
                                System.out.println("Exist mobile :" + response.trim());
                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    Toast.makeText(ChangePasswordActivity.this, ""+j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();

                                    pg.dismiss();
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, ""+j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                    pg.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pg.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pg.dismiss();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("phone",sharedPreferences.getString(Config.contactnumber1,"") );
                    map.put("oldpassword", edCurrentPassword.getText().toString());
                    map.put("newpassword", edNewPassword.getText().toString());

                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
}
