package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

public class MobileRegisterActivity extends AppCompatActivity {
    EditText edMobileNumber;
    Button btnMobileRegister;
    ProgressDialog pg;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_register);
        findViewByIdS();

        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MobileRegisterActivity.this);
        editor = sharedPreferences.edit();

        btnMobileRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CheckExistMobile().execute();

            }
        });
    }

    private void findViewByIdS() {
        edMobileNumber = findViewById(R.id.edMobileNumber);
        btnMobileRegister = findViewById(R.id.btnMobileRegister);
    }

    private class CheckExistMobile extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(MobileRegisterActivity.this);
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
                                    Intent intent  = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                                    intent.putExtra("mobile", String.valueOf(j.getString(Config.response)));
                                    startActivity(intent);
                                    pg.dismiss();
                                } else {
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
                    map.put("phone", edMobileNumber.getText().toString());
                    System.out.println("Exist mobile " + map);
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
