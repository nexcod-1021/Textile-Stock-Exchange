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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Config;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnSubmit;
    ProgressDialog pg;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

        findViewByIds();

        edUserName.setText("TSET00022");
        edPassword.setText("123456");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginData().execute();
            }
        });

    }

    private void findViewByIds() {
        btnSubmit = (Button) findViewById(R.id.btnMobileRegister);
        edUserName = (EditText) findViewById(R.id.ed_login_username);
        edPassword = (EditText) findViewById(R.id.ed_login_password);
    }

    public void intentForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
    }

    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), MobileRegisterActivity.class));
    }

    private class LoginData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(LoginActivity.this);
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.UrlLogin,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject j = new JSONObject(response);
                                System.out.println("User Login : " + response);

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {

                                    Toast.makeText(LoginActivity.this, "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                    pg.dismiss();
                                    System.out.println("login data : " + j.getString(Config.response));

                                    JSONObject jsonProductObject = new JSONObject(j.getString(Config.response));

                                    editor = sharedPreferences.edit();
                                    editor.putString(Config.Sharedprefuser_id, jsonProductObject.getString("user_id"));
                                    editor.putString(Config.SharedprefTSE_id, jsonProductObject.getString("TSE_id"));
                                    editor.putString(Config.SharedprefUserType, jsonProductObject.getString("user_type"));
                                    editor.putString(Config.Sharedprefprofileimage, jsonProductObject.getString("profileimage"));
                                    editor.putString(Config.Sharedprefname, jsonProductObject.getString("name"));
                                    editor.putString(Config.Sharedprefsurname, jsonProductObject.getString("surname"));
                                    editor.putString(Config.SharedprefName_of_enterprise, jsonProductObject.getString("Name_of_enterprise"));
                                    editor.putString(Config.Sharedprefgst_no, jsonProductObject.getString("gst_no"));
                                    editor.putString(Config.Sharedprefpan_no, jsonProductObject.getString("pan_no"));
                                    editor.putString(Config.Sharedprefadress1, jsonProductObject.getString("adress1"));
                                    editor.putString(Config.Sharedprefarea1, jsonProductObject.getString("area1"));
                                    editor.putString(Config.Sharedprefpincode1, jsonProductObject.getString("pincode1"));
                                    editor.putString(Config.SharedprefAdress2, jsonProductObject.getString("Adress2"));
                                    editor.putString(Config.Sharedprefarea2, jsonProductObject.getString("area2"));
                                    editor.putString(Config.Sharedprefpincode2, jsonProductObject.getString("pincode2"));
                                    editor.putString(Config.Sharedprefemailid, jsonProductObject.getString("emailid"));
                                    editor.putString(Config.Sharedprefpassword, jsonProductObject.getString("password"));
                                    editor.putString(Config.Sharedprefcontactnumber1, jsonProductObject.getString("contactnumber1"));
                                    editor.putString(Config.Sharedprefcontactnumber2, jsonProductObject.getString("contactnumber2"));
                                  //  editor.putString(Config.Sharedprefcategory, jsonProductObject.getString("category"));
                                    editor.putString(Config.Sharedprefsubcategory, jsonProductObject.getString("subcategory"));
                                    editor.putString(Config.SharedprefQty, jsonProductObject.getString("Qty"));
                                    editor.putString(Config.SharedprefMembership, jsonProductObject.getString("Membership"));
                                    editor.putString(Config.Sharedprefsubscription_startdate, jsonProductObject.getString("subscription_startdate"));
                                    editor.putString(Config.Sharedprefsubscription_enddate, jsonProductObject.getString("subscription_enddate"));
                                    editor.putString(Config.SharedprefRefferalcode, jsonProductObject.getString("Refferalcode"));
                                    editor.putString(Config.Sharedprefcash_bak, jsonProductObject.getString("cash_bak"));
                                    editor.putString(Config.Sharedprefimgpath, jsonProductObject.getString("imgpath"));


                                    editor.commit();
                                    finish();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(getApplicationContext(), "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                    System.out.println("" + j.getString("MESSAGE"));
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();

                    map.put("TSE_id", edUserName.getText().toString());
                    map.put("password", edPassword.getText().toString());
                    System.out.println("Data Login : " + map);
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
