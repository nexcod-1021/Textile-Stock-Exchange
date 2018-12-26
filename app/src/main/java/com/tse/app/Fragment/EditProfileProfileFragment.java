package com.tse.app.Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tse.app.Activity.RegistrationForm2;
import com.tse.app.Config;
import com.tse.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditProfileProfileFragment extends android.support.v4.app.Fragment {
    SharedPreferences sharedPreferences;
    EditText edProfilefname, edProfilelname, edProfileUserType, edProfileEmail, edProfileEnterPrise, edProfileGSTno,
            edProfilePanNo, edProfileAdress1, edProfilePincode1, edProfileAdress2, edProfilePincode2,
            edProfileContactNo1, edProfileContactNo2, edProfileReferralcode;
    RadioButton rbProfileGeneral, rbProfilePrime;
    AutoCompleteTextView autoProfileArea1, autoProfileArea2;
    private ArrayList<String> fetch_area_list;
    ProgressDialog pg,pg1;


    public EditProfileProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_profile, container, false);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        edProfilefname = (EditText) view.findViewById(R.id.edProfilefname);
        edProfilelname = (EditText) view.findViewById(R.id.edProfilelname);
        edProfileUserType = (EditText) view.findViewById(R.id.edProfileUserType);
        edProfileEmail = (EditText) view.findViewById(R.id.edProfileEmail);
        edProfileEnterPrise = (EditText) view.findViewById(R.id.edProfileEnterPrise);
        edProfileGSTno = (EditText) view.findViewById(R.id.edProfileGSTno);
        edProfilePanNo = (EditText) view.findViewById(R.id.edProfilePanNo);
        edProfileAdress1 = (EditText) view.findViewById(R.id.edProfileAdress1);
        autoProfileArea1 = (AutoCompleteTextView) view.findViewById(R.id.autoProfileArea1);
        edProfilePincode1 = (EditText) view.findViewById(R.id.edProfilePincode1);
        edProfileAdress2 = (EditText) view.findViewById(R.id.edProfileAdress2);
        autoProfileArea2 = (AutoCompleteTextView) view.findViewById(R.id.autoProfileArea2);
        edProfilePincode2 = (EditText) view.findViewById(R.id.edProfilePincode2);
        edProfileContactNo1 = (EditText) view.findViewById(R.id.edProfileContactNo1);
        edProfileContactNo2 = (EditText) view.findViewById(R.id.edProfileContactNo2);
        edProfileReferralcode = (EditText) view.findViewById(R.id.edProfileReferralcode);
        rbProfileGeneral = (RadioButton) view.findViewById(R.id.rbProfileGeneral);
        rbProfilePrime = (RadioButton) view.findViewById(R.id.rbProfilePrime);

        edProfilefname.setText(sharedPreferences.getString(Config.Sharedprefname, ""));
        edProfilelname.setText(sharedPreferences.getString(Config.Sharedprefsurname, ""));
        edProfileUserType.setText(sharedPreferences.getString(Config.Sharedprefuser_id, ""));
        edProfileEmail.setText(sharedPreferences.getString(Config.Sharedprefemailid, ""));
        edProfileEnterPrise.setText(sharedPreferences.getString(Config.SharedprefName_of_enterprise, ""));
        edProfileGSTno.setText(sharedPreferences.getString(Config.Sharedprefgst_no, ""));
        edProfilePanNo.setText(sharedPreferences.getString(Config.Sharedprefpan_no, ""));
        edProfileAdress1.setText(sharedPreferences.getString(Config.Sharedprefadress1, ""));
        autoProfileArea1.setText(sharedPreferences.getString(Config.Sharedprefarea1, ""));
        edProfilePincode1.setText(sharedPreferences.getString(Config.Sharedprefpincode1, ""));
        edProfileAdress2.setText(sharedPreferences.getString(Config.SharedprefAdress2, ""));
        autoProfileArea2.setText(sharedPreferences.getString(Config.Sharedprefarea2, ""));
        edProfilePincode2.setText(sharedPreferences.getString(Config.Sharedprefpincode2, ""));
        edProfileContactNo1.setText(sharedPreferences.getString(Config.Sharedprefcontactnumber1, ""));
        edProfileContactNo2.setText(sharedPreferences.getString(Config.Sharedprefcontactnumber2, ""));
        edProfileReferralcode.setText(sharedPreferences.getString(Config.SharedprefRefferalcode, ""));
        int positionM = Integer.parseInt(sharedPreferences.getString(Config.Membership, ""));

        if (positionM == 0) {
            rbProfileGeneral.setChecked(true);
            rbProfilePrime.setChecked(false);
        } else {
            rbProfileGeneral.setChecked(false);
            rbProfilePrime.setChecked(true);
        }
        new fetch_area().execute();
        new edit_profile().execute();

        return view;
    }

    private class fetch_area extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(getContext());
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

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
                                                getContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );
                                        autoProfileArea1.setThreshold(1);


                                        autoProfileArea1.setAdapter(Fetch_Area1);
                                        ArrayAdapter<String> Fetch_Area2 = new ArrayAdapter<String>(
                                                getContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );

                                        autoProfileArea2.setThreshold(1);
                                        autoProfileArea2.setAdapter(Fetch_Area2);
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
                    },
                    new Response.ErrorListener() {
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
        protected void onPostExecute(Void aVoid) {

        }
    }
    private class edit_profile extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pg = new ProgressDialog(getContext());
            pg.setTitle("Authentication...");
            pg.setMessage(Config.LoadingMsg);
            pg.show();

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
                                                getContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );
                                        autoProfileArea1.setThreshold(1);


                                        autoProfileArea1.setAdapter(Fetch_Area1);
                                        ArrayAdapter<String> Fetch_Area2 = new ArrayAdapter<String>(
                                                getContext(),
                                                R.layout.dropdowntextview,
                                                fetch_area_list
                                        );

                                        autoProfileArea2.setThreshold(1);
                                        autoProfileArea2.setAdapter(Fetch_Area2);
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
                    },
                    new Response.ErrorListener() {
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
        protected void onPostExecute(Void aVoid) {

        }
    }

}
