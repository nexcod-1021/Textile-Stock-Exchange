package com.tse.app.Fragment;


import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Activity.RegistrationForm2;
import com.tse.app.Adapter.BuyerListAdeptor;
import com.tse.app.Config;
import com.tse.app.Model.Order_Fatch;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;






public class BuyerFragment extends android.support.v4.app.Fragment {

    RecyclerView RecyclerViewDeatailItem;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Order_Fatch> list;
    private BuyerListAdeptor buyerListAdeptor;


    ProgressDialog pg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);


        RecyclerViewDeatailItem = view.findViewById(R.id.RecyclerViewDeatailItem);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        new BuyerDetail_Item().execute();
        return view;
    }

    private class BuyerDetail_Item extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(getContext());
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.get_order_fetch, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject j = new JSONObject(response.trim());
                        System.out.println("Get QTY :" + response.trim());
                        if (j.getString("STATUS").equalsIgnoreCase("true")) {
                            String response1 = j.getString("response");
                            list = new ArrayList<Order_Fatch>();
                            JSONArray arr = new JSONArray(response1);
                            for (int i = 0; i < arr.length(); i++) {
                                Order_Fatch order_fatch = new Order_Fatch();
                                JSONObject jsonProductObject = arr.getJSONObject(i);


                                order_fatch.setOrdercode(jsonProductObject.getString("ordercode"));
                                order_fatch.setCategory(jsonProductObject.getString("category"));
                                order_fatch.setSubcategory(jsonProductObject.getString("subcategory"));
                                order_fatch.setProduct(jsonProductObject.getString("product"));
                                order_fatch.setRate(jsonProductObject.getString("rate"));
                                order_fatch.setDimension(jsonProductObject.getString("dimension"));
                                order_fatch.setGsm(jsonProductObject.getString("gsm"));
                                order_fatch.setColour(jsonProductObject.getString("colour"));
                                order_fatch.setRemark(jsonProductObject.getString("remark"));
                                order_fatch.setQualitiy(jsonProductObject.getString("qualitiy"));
                                order_fatch.setPaymentterms(jsonProductObject.getString("paymentterms"));
                                order_fatch.setArea1(jsonProductObject.getString("area1"));
                                list.add(order_fatch);
                            }

                            buyerListAdeptor = new BuyerListAdeptor(getContext(), list);
                            RecyclerViewDeatailItem.setHasFixedSize(true);
                            RecyclerViewDeatailItem.setLayoutManager(linearLayoutManager);

                            RecyclerViewDeatailItem.setAdapter(buyerListAdeptor);
                            buyerListAdeptor.notifyDataSetChanged();



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
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_id","TSEM00074");
                    map.put("type","1");

                    return map;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
