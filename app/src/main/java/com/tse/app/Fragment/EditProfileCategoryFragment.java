package com.tse.app.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Activity.EditProfileActivity;
import com.tse.app.Activity.MobileRegisterActivity;
import com.tse.app.Activity.VerifyOTPActivity;
import com.tse.app.Adapter.CategoryListAdeptor;
import com.tse.app.Adapter.HomeAdeptor;
import com.tse.app.Config;
import com.tse.app.Model.Category_Model;
import com.tse.app.Model.Order_Fatch;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class
EditProfileCategoryFragment extends android.support.v4.app.Fragment {
    Spinner spProfileSelect_Category, spProfileMeters;
    EditText edProfileSub_Category, edProfileQuantity;
    SharedPreferences sharedPreferences;
    ArrayList<String> cat_name_list;
    ArrayList<String> qty_type_list;
    private ProgressDialog pg, pg2, pg1,pg3;
    FloatingActionButton btnProfileAdd;
    RecyclerView rcEditProfileCategory;
    private CategoryListAdeptor categoryListAdeptor;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Category_Model> list;


    Button btnAddCategory;
    ImageView img_closesheet;

    private BottomSheetBehavior sheetBehavior;
    LinearLayout linearLayout;


    public EditProfileCategoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_category, container, false);
        FindviewBy_Id(view);




        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sheetBehavior = BottomSheetBehavior.from(linearLayout);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                spProfileSelect_Category = (Spinner) bottomSheet.findViewById(R.id.spProfileSelect_Category);
                edProfileSub_Category = (EditText) bottomSheet.findViewById(R.id.edProfileSub_Category);
                edProfileQuantity = (EditText) bottomSheet.findViewById(R.id.edProfileQuantity);
                spProfileMeters = (Spinner) bottomSheet.findViewById(R.id.spProfileMeters);

                btnAddCategory = (Button) bottomSheet.findViewById(R.id.btn_catagory_submit);
                img_closesheet = (ImageView) bottomSheet.findViewById(R.id.img_closesheet);
                img_closesheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else {
                        }
                    }
                });
                spProfileSelect_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorwhite));

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spProfileMeters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorwhite));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnAddCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Validation()) {
                            new addsubcategory().execute();
                        }
                    }
                });

                ArrayAdapter<String> Select_category = new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        cat_name_list
                );

                spProfileSelect_Category.setAdapter(Select_category);

                ArrayAdapter<String> meter_list = new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        qty_type_list
                );

                spProfileMeters.setAdapter(meter_list);


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
        btnProfileAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                } else {

                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        new Get_Category().execute();
        new get_qty().execute();
        new fetchsubcategory().execute();
        return view;
    }

    private void FindviewBy_Id(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.bottom_sheet1);
        btnProfileAdd = (FloatingActionButton) view.findViewById(R.id.btnProfileAdd);
        rcEditProfileCategory = (RecyclerView) view.findViewById(R.id.rcEditProfileCategory);
    }

    public boolean Validation() {
        if (edProfileSub_Category.getText().toString().length() == 0) {
            edProfileSub_Category.setError("Please Enter Sub Category");
            return false;
        } else if (edProfileQuantity.getText().toString().length() == 0) {
            edProfileQuantity.setError("Please Enter Quantity");
            return false;
        }


        return true;

    }

    private class Get_Category extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg2 = new ProgressDialog(getContext());
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
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

    private class get_qty extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(getContext());
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

                        if (j.getString("STATUS").equalsIgnoreCase("true")) {
                            String response1 = j.getString("response");
                            qty_type_list = new ArrayList();
                            JSONArray arr = new JSONArray(response1);
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject jsonProductObject = arr.getJSONObject(i);
                                String cat_name = jsonProductObject.getString("qty_type");
                                qty_type_list.add(cat_name);
                            }


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


            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private class addsubcategory extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg1 = new ProgressDialog(getContext());
            pg1.setTitle("Authentication...");
            pg1.setMessage(Config.LoadingMsg);
            pg1.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.addsubcategory,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                  Intent intent = new Intent(getContext(),EditProfileActivity.class);
                                  startActivity(intent);


                                    Toast.makeText(getContext(), "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();

                                    pg1.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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


                    map.put("user_id", sharedPreferences.getString(Config.SharedprefTSE_id, ""));
                    map.put("cat_id", spProfileSelect_Category.getSelectedItem().toString());
                    map.put("subcategory", edProfileSub_Category.getText().toString());
                    map.put("qty", edProfileQuantity.getText().toString()+spProfileMeters.getSelectedItem().toString());
                    map.put("productname", "data");


                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

    private class fetchsubcategory extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg3 = new ProgressDialog(getContext());
            pg3.setTitle("Authentication...");
            pg3.setMessage(Config.LoadingMsg);
            pg3.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.fetchsubcategory,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject j = new JSONObject(response.trim());
                                System.out.println("Exist mobile :" + response.trim());
                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    String response1 = j.getString("response");
                                    list = new ArrayList<Category_Model>();


                                    JSONArray arr = new JSONArray(response1);
                                    for (int i = 0; i < arr.length(); i++) {
                                        Category_Model category_model = new Category_Model();
                                        JSONObject jsonProductObject = arr.getJSONObject(i);


                                        category_model.setId(jsonProductObject.getString("id"));
                                        category_model.setUser_id(jsonProductObject.getString("user_id"));
                                        category_model.setCat_id(jsonProductObject.getString("cat_id"));
                                        category_model.setSubcategory(jsonProductObject.getString("subcategory"));
                                        category_model.setQty(jsonProductObject.getString("qty"));
                                        category_model.setProductname(jsonProductObject.getString("productname"));
                                        category_model.setQty_type(jsonProductObject.getString("qty_type"));


                                        list.add(category_model);

                                    }

                                    categoryListAdeptor  = new CategoryListAdeptor(getContext(), list);
                                    rcEditProfileCategory.setHasFixedSize(true);
                                    rcEditProfileCategory.setLayoutManager(linearLayoutManager);

                                    rcEditProfileCategory.setAdapter(categoryListAdeptor);
                                    categoryListAdeptor.notifyDataSetChanged();
                                    pg3.dismiss();
                                } else {
                                    Toast.makeText(getContext(), ""+j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_id", sharedPreferences.getString(Config.SharedprefTSE_id,""));



                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

}
