package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.AndroidMultiPartEntity;
import com.tse.app.Config;
import com.tse.app.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationForm2 extends AppCompatActivity {
    Spinner spRegisterSelectCategory, spRegisterMeters;
    AutoCompleteTextView autoRegisterArea1, autoRegisterArea2;
    EditText edRegisterAddress1, edRegisterPincode1, edRegisterAddress2,
            edRegisterPincode2, edRegisterContactNo1, edRegisterContactNo2,
            edRegisterSubCategory, edRegisterQuantity, edRegisterReferralCode;
    RadioButton rbRegisterGeneral, rbRegisterPrime;
    ProgressDialog pg, pg2, pg3,pg4;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ArrayList<String> cat_name_list;
    private ArrayList<String> qty_type_list;
    private ArrayList<String> fetch_area_list;

    long totalSize = 0;

    ProgressDialog dialog;
    private int PICK_PDF_REQUEST = 1;
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    String selectedFilePath;
    String MemberType = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form2);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegistrationForm2.this);
        editor = sharedPreferences.edit();
        Findviewby_Id();

        //  edRegisterContactNo1.setText(getIntent().getExtras().getString(Config.Contact1));
        edRegisterContactNo1.setEnabled(false);

        // selectedFilePath = getIntent().getExtras().getString(Config.profileUrl);


        new Get_price().execute();
        new Get_Category().execute();
        new get_qty().execute();
        new fetch_area().execute();
        new check_refferal().execute();

        MemberType = rbRegisterGeneral.getText().toString();

        rbRegisterGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberType = rbRegisterGeneral.getText().toString();
            }
        });
        rbRegisterPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberType = rbRegisterPrime.getText().toString();
            }
        });


        spRegisterSelectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorBlack));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spRegisterMeters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spRegisterSelectCategory = (Spinner) findViewById(R.id.spRegistrationSelectCategory);
        spRegisterMeters = (Spinner) findViewById(R.id.spRegistrationMeters);
        rbRegisterGeneral = (RadioButton) findViewById(R.id.rbRegistrationGeneral);
        rbRegisterPrime = (RadioButton) findViewById(R.id.rbRegistrationPrime);
        edRegisterAddress1 = (EditText) findViewById(R.id.edRegistrationAddress1);
        autoRegisterArea1 = (AutoCompleteTextView) findViewById(R.id.edRegistrationArea1);
        edRegisterPincode1 = (EditText) findViewById(R.id.edRegistrationPincode1);
        edRegisterAddress2 = (EditText) findViewById(R.id.edRegistrationAddress2);
        autoRegisterArea2 = (AutoCompleteTextView) findViewById(R.id.edRegistrationArea2);
        edRegisterPincode2 = (EditText) findViewById(R.id.edRegistrationPincode2);
        edRegisterContactNo1 = (EditText) findViewById(R.id.edRegistrationContactNo1);
        edRegisterContactNo2 = (EditText) findViewById(R.id.edRegistrationContactNo2);
        edRegisterSubCategory = (EditText) findViewById(R.id.edRegistrationSubCategory);
        edRegisterQuantity = (EditText) findViewById(R.id.edRegistrationQuantity);
        edRegisterReferralCode = (EditText) findViewById(R.id.edRegistrationReferralCode);

    }

    public void movelogin(View view) {
        if (Validation()) {
            dialog = ProgressDialog.show(RegistrationForm2.this, "", Config.LoadingMsg, true);
            new RegisterUser().execute();
        }
    }

    public boolean Validation() {
        if (edRegisterAddress1.getText().toString().length() == 0) {
            edRegisterAddress1.setError("Please Enter Address 1");
            return false;
        } else if (autoRegisterArea1.getText().toString().length() == 0) {
            autoRegisterArea1.setError("Please Enter Area 1");
            return false;
        } else if (edRegisterPincode1.getText().toString().length() == 0) {
            edRegisterPincode1.setError("Please Enter Pincode 1");
            return false;
        } else if (edRegisterPincode1.getText().toString().length() != 6) {
            edRegisterPincode1.setError("Please Enter Valid Pincode");
            return false;
        }

        /*else if (edRegisterAddress2.getText().toString().length() == 0) {
            edRegisterAddress2.setError("Please Enter Address 2");
            return false;
        } else if (edRegisterArea2.getText().toString().length() == 0) {
            edRegisterArea2.setError("Please Enter Area 2");
            return false;
        } else if (edRegisterPincode2.getText().toString().length() == 0) {
            edRegisterPincode2.setError("Please Enter Pincode 2");
            return false;
        } else if (edRegisterContactNo1.getText().toString().length() == 0) {
            edRegisterContactNo1.setError("Please Enter Contact No. 1");
            return false;
        }
        else if (edRegisterContactNo2.getText().toString().length() == 0) {
            edRegisterContactNo2.setError("Please Enter  Contact No. 2");
            return false;
        }
        else if (edRegisterSubCategory.getText().toString().length() == 0) {
            edRegisterSubCategory.setError("Please Enter Sub category");
            return false;
        } */
        else if (edRegisterQuantity.getText().toString().length() == 0) {
            edRegisterQuantity.setError("Please Enter Quantity");
            return false;
        }
        /*else if (edRegisterReferralCode.getText().toString().length() == 0) {
            edRegisterReferralCode.setError("Please Enter Referral Code");
            return false;
        }*/
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
            pg2.setTitle("Authentication...");
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
                                            R.layout.dropdowntextview,
                                            cat_name_list
                                    );

                                    spRegisterSelectCategory.setAdapter(Select_category);

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
            spRegisterSelectCategory.setAdapter(Select_category);*/
        }
    }

    private class get_qty extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(RegistrationForm2.this);
            pg.setTitle("Authentication...");
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

                            spRegisterMeters.setAdapter(Select_category);
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

    private class RegisterUser extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Config.BaseUrl + Config.UrlRegister);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                if (!TextUtils.isEmpty(selectedFilePath)) {
                    File sourceFile = new File(selectedFilePath);
                    entity.addPart(Config.profileimage, new FileBody(sourceFile));
                }

                entity.addPart(Config.user, new StringBody(getIntent().getExtras().getString(Config.UserType)));
                entity.addPart(Config.name, new StringBody(getIntent().getExtras().getString(Config.fName)));
                entity.addPart(Config.surname, new StringBody(getIntent().getExtras().getString(Config.lName)));
                entity.addPart(Config.Name_of_enterprise, new StringBody(getIntent().getExtras().getString(Config.nameOfEnterprice)));
                entity.addPart(Config.gst_no, new StringBody(getIntent().getExtras().getString(Config.GstNo)));
                entity.addPart(Config.pan_no, new StringBody(getIntent().getExtras().getString(Config.PanNo)));
                entity.addPart(Config.adress1, new StringBody(edRegisterAddress1.getText().toString()));
                entity.addPart(Config.area1, new StringBody(autoRegisterArea1.getText().toString()));
                entity.addPart(Config.pincode1, new StringBody(edRegisterPincode1.getText().toString()));
                entity.addPart(Config.adress2, new StringBody(edRegisterAddress2.getText().toString()));
                entity.addPart(Config.area2, new StringBody(autoRegisterArea2.getText().toString()));
                entity.addPart(Config.pincode2, new StringBody(edRegisterPincode2.getText().toString()));
                entity.addPart(Config.emailid, new StringBody(getIntent().getExtras().getString(Config.Email)));
                entity.addPart(Config.contactnumber1, new StringBody(edRegisterContactNo1.getText().toString()));
                entity.addPart(Config.contactnumber2, new StringBody(edRegisterContactNo2.getText().toString()));
                entity.addPart(Config.category, new StringBody("1"));
                entity.addPart(Config.subcategory, new StringBody(edRegisterSubCategory.getText().toString()));
                entity.addPart(Config.qty, new StringBody(edRegisterQuantity.getText().toString() + " " + spRegisterMeters.getSelectedItem().toString()));
                entity.addPart(Config.Membership, new StringBody(MemberType.toLowerCase()));
                entity.addPart(Config.password, new StringBody(getIntent().getExtras().getString(Config.password)));
                entity.addPart(Config.Refferalcode, new StringBody(edRegisterReferralCode.getText().toString()));


                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseString = EntityUtils.toString(r_entity);
                    selectedFilePath = "";
                    dialog.dismiss();
                    System.out.println("Response : " + responseString);

                } else {
                    dialog.dismiss();
                    responseString = "Error occurred! Http Status Code: " + statusCode;
                }
            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }
            return responseString.trim();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG, "Response from server: " + result);
            JSONObject object = null;
            try {
                object = new JSONObject(result);

                if (object.getString("STATUS").equalsIgnoreCase("true")) {
                    Toast.makeText(RegistrationForm2.this, "" + object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(RegistrationForm2.this, "" + object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }

    }

    private class fetch_area extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg3 = new ProgressDialog(RegistrationForm2.this);
            pg3.setTitle("Authentication...");
            pg3.setMessage(Config.LoadingMsg);
            pg3.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_fetch_area,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    String response1 = j.getString("response");
                                    fetch_area_list = new ArrayList();
                                    JSONArray arr = new JSONArray(response1);

                                    for (int i = 0; i < arr.length(); i++) {
                                        String data = arr.get(i).toString();
                                        fetch_area_list.add(data);

                                        ArrayAdapter<String> Fetch_Area1 = new ArrayAdapter<String>(
                                                getApplicationContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );
                                        autoRegisterArea1.setThreshold(1);


                                        autoRegisterArea1.setAdapter(Fetch_Area1);
                                        ArrayAdapter<String> Fetch_Area2 = new ArrayAdapter<String>(
                                                getApplicationContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );

                                        autoRegisterArea2.setThreshold(1);
                                        autoRegisterArea2.setAdapter(Fetch_Area2);
                                    }

                                    pg3.dismiss();
                                } else {

                                    pg3.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pg3.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pg3.dismiss();

                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
    private class check_refferal  extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg4 = new ProgressDialog(RegistrationForm2.this);
            pg4.setTitle("Authentication...");
            pg4.setMessage(Config.LoadingMsg);
            pg4.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_check_refferal,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    String response1 = j.getString("refferal%");
                                    edRegisterReferralCode.setText(response1);


                                    pg4.dismiss();
                                } else {

                                    pg4.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pg4.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pg4.dismiss();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("tseid", "TSEM00010");

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