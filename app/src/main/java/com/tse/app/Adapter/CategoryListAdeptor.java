package com.tse.app.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.tse.app.Config;
import com.tse.app.Fragment.EditProfileCategoryFragment;
import com.tse.app.Fragment.TraderBuyerFragment;
import com.tse.app.Model.Category_Model;
import com.tse.app.Model.Order_Fatch;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryListAdeptor extends RecyclerView.Adapter<CategoryListAdeptor.MyViewHolder> {

    private Context context;
    private List<Category_Model> list;
    private ProgressDialog pg3;
    String id;



    public CategoryListAdeptor(Context context, List<Category_Model> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categorylist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Category_Model category_model = list.get(position);
        holder.TxtProfileSelect_Category.setText(category_model.getCat_id());
        holder.TxtProfileSub_Category.setText(category_model.getSubcategory());
        holder.TxtProfileQty.setText(category_model.getQty());
        holder.TxtProfileproductname.setText(category_model.getProductname());
        holder.btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id=  list.get(position).getId();

                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();

                new deletesubcat().execute();
            }
        });
        holder.btnEditCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent intent=new Intent(context.getApplicationContext(), EditProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.getApplicationContext().startActivity(intent);*/


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TxtProfileSelect_Category, TxtProfileSub_Category, TxtProfileQty, TxtProfileproductname;
        ImageButton btnEditCategory, btnDeleteCategory;

        public MyViewHolder(View view) {

            super(view);
            TxtProfileSelect_Category = (TextView) view.findViewById(R.id.TxtProfileSelect_Category);
            TxtProfileSub_Category = (TextView) view.findViewById(R.id.TxtProfileSub_Category);
            TxtProfileQty = (TextView) view.findViewById(R.id.TxtProfileQty);
            TxtProfileproductname = (TextView) view.findViewById(R.id.TxtProfileproductname);
            btnEditCategory = (ImageButton) view.findViewById(R.id.btnEditCategory);
            btnDeleteCategory = (ImageButton) view.findViewById(R.id.btnDeleteCategory);

        }
    }

    private class deletesubcat extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg3 = new ProgressDialog(context);
            pg3.setTitle("Authentication...");
            pg3.setMessage(Config.LoadingMsg);
            pg3.show();

        }

        @Override
        protected Void doInBackground(final Void... email) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.BaseUrl + Config.deletesubcat,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject j = new JSONObject(response.trim());

                                if (j.getString("STATUS").equalsIgnoreCase("true")) {
                                    Intent intent=new Intent(context.getApplicationContext(), EditProfileCategoryFragment.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                    context.getApplicationContext().startActivity(intent);
                                    Toast.makeText(context, "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                    pg3.dismiss();
                                } else {
                                    Toast.makeText(context, "" + j.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
                    map.put("cat_id", id);


                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }



    }


}