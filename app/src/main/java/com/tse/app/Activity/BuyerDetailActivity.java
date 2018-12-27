package com.tse.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Config;
import com.tse.app.Fragment.EditProfileProfileFragment;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuyerDetailActivity extends AppCompatActivity {

    private BottomSheetBehavior sheetBehavior;
    LinearLayout linearLayout;
    ImageView close;
    TextView edExpectedQty, edExpectedRate, edRemark;
    ProgressDialog pg,pg1;
    Button btnBuyerOffer,btnSubmitOffer,btnSendOffer;
    private  Intent intent;
    TextView TxtOrderDeatailCode, TxtOrderDetailProduct, TxtOrderDetailCategory, TxtOrderDetailSubCategory, TxtOrderDetailCity,
            TxtOrderDetailArea, TxtOrderDetailDiemension, TxtOrderDetailGSM, TxtOrderDetailColour, TxtOrderDetailRemark,
            TxtOrderDetailQuality, TxtOrderDetailPaymentTerms ,TxtOrderDetailStatus;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_detail);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BuyerDetailActivity.this);
        findViewBy_Id();
        intent = getIntent();
        TxtOrderDeatailCode.setText(intent.getStringExtra("ordercode"));
        TxtOrderDetailProduct.setText(intent.getStringExtra("product"));
        TxtOrderDetailCategory.setText(intent.getStringExtra("category"));
        TxtOrderDetailSubCategory.setText(intent.getStringExtra("subcategory"));
        TxtOrderDetailArea.setText(intent.getStringExtra("area1"));
        TxtOrderDetailDiemension.setText(intent.getStringExtra("Dimension"));
        TxtOrderDetailGSM.setText(intent.getStringExtra("Gsm"));
        TxtOrderDetailColour.setText(intent.getStringExtra("Colour"));
        TxtOrderDetailRemark.setText(intent.getStringExtra("Remark"));
        TxtOrderDetailQuality.setText(intent.getStringExtra("Qualitiy"));
        TxtOrderDetailPaymentTerms.setText(intent.getStringExtra("Paymentterms"));


        sheetBehavior = BottomSheetBehavior.from(linearLayout);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                close = bottomSheet.findViewById(R.id.img_close);
                edExpectedQty = bottomSheet.findViewById(R.id.edExpectedQty);
                edExpectedRate = bottomSheet.findViewById(R.id.edExpectedRate);
                edRemark = bottomSheet.findViewById(R.id.edRemark);
                btnSubmitOffer = (Button) bottomSheet.findViewById(R.id.btnSubmitOffer);

                edRemark.setText(intent.getStringExtra("Remark"));
                edExpectedQty.setText(intent.getStringExtra("Qualitiy"));
                edExpectedRate.setText(intent.getStringExtra("rate"));
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else {
                        }
                    }
                });
                btnSubmitOffer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new offer().execute();
                    }
                });


                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        btnBuyerOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                } else {

                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        btnSendOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new offer_data().execute();
            }
        });
    }


    private void findViewBy_Id() {


        linearLayout = (LinearLayout) findViewById(R.id.bottom_sheet);
        btnBuyerOffer = (Button) findViewById(R.id.btnBuyerOffer);
        btnSendOffer = (Button) findViewById(R.id.btnSendOffer);
        TxtOrderDeatailCode = (TextView) findViewById(R.id.TxtOrderDeatailCode);
        TxtOrderDetailProduct = (TextView) findViewById(R.id.TxtOrderDetailProduct);
        TxtOrderDetailCategory = (TextView) findViewById(R.id.TxtOrderDetailCategory);
        TxtOrderDetailSubCategory = (TextView) findViewById(R.id.TxtOrderDetailSubCategory);
        TxtOrderDetailCity = (TextView) findViewById(R.id.TxtOrderDetailCity);
        TxtOrderDetailArea = (TextView) findViewById(R.id.TxtOrderDetailArea);
        TxtOrderDetailDiemension = (TextView) findViewById(R.id.TxtOrderDetailDiemension);
        TxtOrderDetailGSM = (TextView) findViewById(R.id.TxtOrderDetailGSM);
        TxtOrderDetailColour = (TextView) findViewById(R.id.TxtOrderDetailColour);
        TxtOrderDetailRemark = (TextView) findViewById(R.id.TxtOrderDetailRemark);
        TxtOrderDeatailCode = (TextView) findViewById(R.id.TxtOrderDeatailCode);
        TxtOrderDetailQuality = (TextView) findViewById(R.id.TxtOrderDetailQuality);
        TxtOrderDetailPaymentTerms = (TextView) findViewById(R.id.TxtOrderDetailPaymentTerms);
        TxtOrderDetailStatus = (TextView) findViewById(R.id.TxtOrderDetailStatus);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add) {

        }
        return super.onOptionsItemSelected(item);
    }
   private class offer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(BuyerDetailActivity.this);
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.offer,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {




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
                    map.put("userid", sharedPreferences.getString(Config.SharedprefTSE_id,""));
                    map.put("ordercode", TxtOrderDeatailCode.getText().toString());
                    map.put("expectedqty", edExpectedQty.getText().toString());
                    map.put("expectedrate", edExpectedRate.getText().toString());
                    map.put("remarks", edRemark.getText().toString());

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
    private class offer_data extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg1 = new ProgressDialog(BuyerDetailActivity.this);
            pg1.setTitle("Authentication...");
            pg1.setMessage(Config.LoadingMsg);
            pg1.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.offer,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {


                                    pg1.dismiss();
                                } else {

                                    pg1.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pg1.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pg1.dismiss();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("userid", sharedPreferences.getString(Config.SharedprefTSE_id,""));
                    map.put("ordercode", TxtOrderDeatailCode.getText().toString());
                    map.put("expectedqty", TxtOrderDetailQuality.getText().toString());
                    map.put("expectedrate", intent.getStringExtra("rate"));
                    map.put("remarks", TxtOrderDetailRemark.getText().toString());

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
