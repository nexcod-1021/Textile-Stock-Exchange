package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.Resource;
import com.tse.app.Config;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class RegistrationForm2 extends AppCompatActivity {
    Spinner spRegistrationSelectCategory, spRegistrationMeters;
    EditText edRegistrationAddress1, edRegistrationArea1, edRegistrationPincode1, edRegistrationAddress2,
            edRegistrationArea2, edRegistrationPincode2, edRegistrationContactNo1, edRegistrationContactNo2,
            edRegistrationSubCategory, edRegistrationQuantity, edRegistrationReferralCode;
    RadioButton rbRegistrationGeneral, rbRegistrationPrime;
    ProgressDialog pg, pg2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<String> cat_name_list;
    ArrayList<String> qty_type_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form2);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegistrationForm2.this);
        editor = sharedPreferences.edit();

        Findviewby_Id();

        new Get_price().execute();
        new Get_Category().execute();
        new get_qty().execute();
        spRegistrationSelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlack));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spRegistrationMeters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlack));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void Findviewby_Id() {
        spRegistrationSelectCategory = (Spinner) findViewById(R.id.spRegistrationSelectCategory);
        spRegistrationMeters = (Spinner) findViewById(R.id.spRegistrationMeters);
        rbRegistrationGeneral = (RadioButton) findViewById(R.id.rbRegistrationGeneral);
        rbRegistrationPrime = (RadioButton) findViewById(R.id.rbRegistrationPrime);
        edRegistrationAddress1 = (EditText) findViewById(R.id.edRegistrationAddress1);
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
        if (Validation()) {

            Intent intent = new Intent(RegistrationForm2.this, LoginActivity.class);
            startActivity(intent);
        }


    }

    public boolean Validation() {
        if (edRegistrationAddress1.getText().toString().length() == 0) {
            edRegistrationAddress1.setError("Please Enter Address 1");
            return false;
        } else if (edRegistrationArea1.getText().toString().length() == 0) {
            edRegistrationArea1.setError("Please Enter Area 1");
            return false;
        } else if (edRegistrationPincode1.getText().toString().length() == 0) {
            edRegistrationPincode1.setError("Please Enter Pincode 1");
            return false;
        } else if (edRegistrationAddress2.getText().toString().length() == 0) {
            edRegistrationAddress2.setError("Please Enter Address 2");
            return false;
        } else if (edRegistrationArea2.getText().toString().length() == 0) {
            edRegistrationArea2.setError("Please Enter Area 2");
            return false;
        } else if (edRegistrationPincode2.getText().toString().length() == 0) {
            edRegistrationPincode2.setError("Please Enter Pincode 2");
            return false;
        } else if (edRegistrationAddress1.getText().toString().length() == 0) {
            edRegistrationAddress1.setError("Please Enter Address 1");
            return false;
        } else if (edRegistrationContactNo1.getText().toString().length() == 0) {
            edRegistrationContactNo1.setError("Please Enter Contact No. 1");
            return false;
        } else if (edRegistrationContactNo2.getText().toString().length() == 0) {
            edRegistrationContactNo2.setError("Please Enter  Contact No. 2");
            return false;
        } else if (edRegistrationSubCategory.getText().toString().length() == 0) {
            edRegistrationSubCategory.setError("Please Enter Sub category");
            return false;
        } else if (edRegistrationQuantity.getText().toString().length() == 0) {
            edRegistrationQuantity.setError("Please Enter Quantity");
            return false;
        } else if (edRegistrationReferralCode.getText().toString().length() == 0) {
            edRegistrationReferralCode.setError("Please Enter Referral Code");
            return false;
        }
        return true;

    }

    private class Get_price extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_price, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject j = new JSONObject(response.trim());
                        System.out.println("Get Price :" + response.trim());
                        if (j.getString("STATUS").equalsIgnoreCase("true")) {

                            String response1 = j.getString("response");


                            JSONArray arr = new JSONArray(response1);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonProductObject = arr.getJSONObject(i);


                                if (jsonProductObject.getString("price_type").equals("general")) {
                                    editor.putString(Config.spPriceGeneral, jsonProductObject.getString("price"));
                                }
                                if (jsonProductObject.getString("price_type").equals("prime")) {
                                    editor.putString(Config.spPricePrime, jsonProductObject.getString("price"));
                                }
                                if (jsonProductObject.getString("price_type").equals("refferal")) {
                                    editor.putString(Config.spPriceRefferal, jsonProductObject.getString("price"));
                                }
                                if (jsonProductObject.getString("price_type").equals("servicemanfees")) {
                                    editor.putString(Config.spPriceServicemanFees, jsonProductObject.getString("price"));
                                }

                            }
                            editor.commit();


                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener()

            { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            RequestQueue ExampleRequestQueue = Volley.newRequestQueue(RegistrationForm2.this);
            ExampleRequestQueue.add(ExampleStringRequest);

            return null;
        }
    }

    private class Get_Category extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg2 = new ProgressDialog(RegistrationForm2.this);
            pg2.setTitle("Authentication...2");
            pg2.setMessage(Config.LoadingMsg);
            pg2.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_category,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject j = new JSONObject(response.trim());
                                System.out.println("Get Category :" + response.trim());
                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    String response1 = j.getString("response");
                                    cat_name_list = new ArrayList();
                                    JSONArray arr = new JSONArray(response1);
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject jsonProductObject = arr.getJSONObject(i);
                                        String cat_name = jsonProductObject.getString("cat_name");
                                        cat_name_list.add(cat_name);
                                    }
                                    ArrayAdapter<String> Select_category = new ArrayAdapter<String>(
                                            getApplicationContext(),
                                            android.R.layout.simple_list_item_1,
                                            cat_name_list
                                    );

                                    spRegistrationSelectCategory.setAdapter(Select_category);

                                    pg2.dismiss();

                                } else {
                                    pg2.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pg2.dismiss();

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pg2.dismiss();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(RegistrationForm2.this);
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
           /* ArrayAdapter<String> Select_category = new ArrayAdapter<String>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_item,
                    cat_name_list
            );
            spRegistrationSelectCategory.setAdapter(Select_category);*/
        }
    }

    private class get_qty extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(RegistrationForm2.this);
            pg.setTitle("Authentication...1");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_qty, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject j = new JSONObject(response.trim());
                        System.out.println("Get QTY :" + response.trim());
                        if (j.getString("STATUS").equalsIgnoreCase("true")) {
                            String response1 = j.getString("response");
                            qty_type_list = new ArrayList();
                            JSONArray arr = new JSONArray(response1);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonProductObject = arr.getJSONObject(i);
                                String cat_name = jsonProductObject.getString("qty_type");
                                qty_type_list.add(cat_name);
                            }
                            ArrayAdapter<String> Select_category = new ArrayAdapter<String>(
                                    getApplicationContext(),
                                    android.R.layout.simple_list_item_1,
                                    qty_type_list
                            );

                            spRegistrationMeters.setAdapter(Select_category);
                            pg.dismiss();

                        } else {
                            pg.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        pg.dismiss();
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pg.dismiss();
                }
            });


            RequestQueue requestQueue = Volley.newRequestQueue(RegistrationForm2.this);
            requestQueue.add(stringRequest);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


}
