package com.tse.app.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tse.app.Activity.EditProfileActivity;
import com.tse.app.Activity.LoginActivity;
import com.tse.app.Activity.RegistrationForm2;
import com.tse.app.AndroidMultiPartEntity;
import com.tse.app.Config;
import com.tse.app.FilePath;
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
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class EditProfileProfileFragment extends android.support.v4.app.Fragment {
    SharedPreferences sharedPreferences;
    EditText edProfilefname, edProfilelname, edProfileUserType, edProfileEmail, edProfileEnterPrise, edProfileGSTno,
            edProfilePanNo, edProfileAdress1, edProfilePincode1, edProfileAdress2, edProfilePincode2,
            edProfileContactNo1, edProfileContactNo2, edProfileReferralcode;
    RadioButton rbProfileGeneral, rbProfilePrime;
    Button btnMobileRegister;
    LinearLayout lvMembership;
    AutoCompleteTextView autoProfileArea1, autoProfileArea2;
    private ArrayList<String> fetch_area_list;
    ProgressDialog pg, pg1;
    private int PICK_PDF_REQUEST = 1;
    String selectedFilePath = "";
    ImageView IvProfileImage;
    TextView TextEditProfile;
    long totalSize = 0;
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    SharedPreferences.Editor editor;


    public EditProfileProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile_profile, container, false);
        findviewByIdS(view);
        IvProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        editor = sharedPreferences.edit();
        edProfilefname.setText(sharedPreferences.getString(Config.Sharedprefname, ""));
        edProfilelname.setText(sharedPreferences.getString(Config.Sharedprefsurname, ""));

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

        if (sharedPreferences.getString(Config.SharedprefUserType, "").equals("M")) {
            edProfileUserType.setText("Manufacture");
        } else if (sharedPreferences.getString(Config.SharedprefUserType, "").equals("T")) {
            edProfileUserType.setText("Traders");
        } else if (sharedPreferences.getString(Config.SharedprefUserType, "").equals("S")) {
            edProfileUserType.setText("Service");
        } else {
            edProfileUserType.setText("Broker");
        }


        if (sharedPreferences.getString(Config.Sharedprefprofileimage, "").equals("")) {
            sharedPreferences.getString(Config.Sharedprefname, "");
            TextEditProfile.setVisibility(View.VISIBLE);

            TextEditProfile.setText(sharedPreferences.getString(Config.Sharedprefname, "").substring(0, 1) + sharedPreferences.getString(Config.Sharedprefsurname, "").substring(0, 1));

        } else {
            TextEditProfile.setVisibility(View.GONE);
            Glide.with(getContext()).
                    load(sharedPreferences.getString(Config.Sharedprefimgpath, "")).centerCrop().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    into(IvProfileImage);
        }

        if (positionM == 1) {
            rbProfileGeneral.setChecked(true);
            rbProfilePrime.setChecked(false);
        } else if (positionM == 2){
            rbProfileGeneral.setChecked(false);
            rbProfilePrime.setChecked(true);
        }
        else {
            lvMembership.setVisibility(View.GONE);

        }
        new fetch_area().execute();
        btnMobileRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    pg1 = ProgressDialog.show(getContext(), "", Config.LoadingMsg, true);
                    new editprofile().execute();
                }
            }
        });


        return view;
    }

    private void findviewByIdS(View view) {
        IvProfileImage = (ImageView) ((EditProfileActivity) getActivity()).findViewById(R.id.cv_profile_image);
        TextEditProfile = (TextView) ((EditProfileActivity) getActivity()).findViewById(R.id.TextEditProfile);
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
        btnMobileRegister = (Button) view.findViewById(R.id.btnMobileRegister);
        lvMembership = (LinearLayout) view.findViewById(R.id.lv_membership);

    }


    public boolean validation() {
        if (edProfilefname.getText().toString().length() == 0) {
            edProfilefname.setError("Please enter First Name");
            return false;
        } else if (edProfilelname.getText().toString().length() == 0) {
            edProfilelname.setError("Please enter Last Name");
            return false;
        }

        else if (edProfileEnterPrise.getText().toString().length() == 0) {
            edProfileEnterPrise.setError("Please enter EnterPrise");
            return false;
        }
        else if (autoProfileArea1.getText().toString().length() == 0) {
            autoProfileArea1.setError("Please Enter Area 1");
            return false;
        }


        if (edProfileAdress1.getText().toString().length() == 0) {
            edProfileAdress1.setError("Please Enter Address 1");
            return false;
        } else if (edProfilePincode1.getText().toString().length() == 0) {
            edProfilePincode1.setError("Please Enter Pincode 1");
            return false;
        } else if (edProfilePincode1.getText().toString().length() != 6) {
            edProfilePincode1.setError("Please Enter Valid Pincode");
            return false;
        } else if (edProfilePincode2.getText().toString().length() == 0) {
            edProfilePincode2.setError("Please Enter Pincode 2");
            return false;
        } else if (edProfilePincode2.getText().toString().length() != 6) {
            edProfilePincode2.setError("Please Enter Valid Pincode");
            return false;
        }

        return true;
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

    private class editprofile extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Config.BaseUrl + Config.edit_profile);

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
                    entity.addPart("profileimage", new FileBody(sourceFile));
                }


                entity.addPart("user_id", new StringBody(sharedPreferences.getString(Config.SharedprefTSE_id, "")));
                entity.addPart("name", new StringBody(edProfilefname.getText().toString()));
                entity.addPart("surname", new StringBody(edProfilelname.getText().toString()));
                entity.addPart("Name_of_enterprise", new StringBody(edProfileEnterPrise.getText().toString()));
                entity.addPart("gst_no", new StringBody(edProfileGSTno.getText().toString()));
                entity.addPart("pan_no", new StringBody(edProfilePanNo.getText().toString()));
                entity.addPart("adress1", new StringBody(edProfileAdress1.getText().toString()));
                entity.addPart("area1", new StringBody(autoProfileArea1.getText().toString()));
                entity.addPart("pincode1", new StringBody(edProfilePincode1.getText().toString()));
                entity.addPart("Adress2", new StringBody(edProfileAdress2.getText().toString()));
                entity.addPart("area2", new StringBody(autoProfileArea2.getText().toString()));
                entity.addPart("pincode2", new StringBody(edProfilePincode2.getText().toString()));
                entity.addPart("emailid", new StringBody(edProfileEmail.getText().toString()));
                entity.addPart("contactnumber2", new StringBody(edProfileContactNo2.getText().toString()));


                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseString = EntityUtils.toString(r_entity);
                    selectedFilePath = "";
                    pg1.dismiss();

                    System.out.println("Response : " + responseString);

                } else {
                    pg1.dismiss();
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
                    JSONObject jsonProductObject = new JSONObject(object.getString(Config.response));


                    Toast.makeText(getContext(), "update successfully", Toast.LENGTH_SHORT).show();
                    Glide.with(getContext()).
                            load(jsonProductObject.getString("imgpath")).centerCrop().
                            diskCacheStrategy(DiskCacheStrategy.ALL).
                            into(IvProfileImage);

                    editor.putString(Config.Sharedprefname, edProfilefname.getText().toString());
                    editor.putString(Config.Sharedprefsurname, edProfilelname.getText().toString());
                    editor.putString(Config.SharedprefName_of_enterprise, edProfileEnterPrise.getText().toString());
                    editor.putString(Config.Sharedprefgst_no, edProfileGSTno.getText().toString());
                    editor.putString(Config.Sharedprefpan_no, edProfilePanNo.getText().toString());
                    editor.putString(Config.Sharedprefadress1, edProfileAdress1.getText().toString());
                    editor.putString(Config.Sharedprefarea1, autoProfileArea1.getText().toString());
                    editor.putString(Config.Sharedprefpincode1, edProfilePincode1.getText().toString());
                    editor.putString(Config.SharedprefAdress2, edProfileAdress2.getText().toString());
                    editor.putString(Config.Sharedprefarea2, autoProfileArea2.getText().toString());
                    editor.putString(Config.Sharedprefpincode2, edProfilePincode2.getText().toString());
                    editor.putString(Config.Sharedprefemailid, edProfileEmail.getText().toString());
                    editor.putString(Config.Sharedprefcontactnumber2, edProfileContactNo2.getText().toString());
                    editor.putString(Config.Sharedprefimgpath, jsonProductObject.getString("imgpath"));

                    editor.commit();


                    //startActivity(new Intent(getContext(), EditProfileActivity.class));
                } else {
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }

    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose File"), PICK_PDF_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedFileUri = data.getData();
            selectedFilePath = FilePath.getPath(getContext(), selectedFileUri);
            Glide.with(getContext()).load(selectedFilePath).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(IvProfileImage);
            Log.i(TAG, "Selected File Path:" + selectedFilePath);
        }
    }


    //method to get the file path from uri


}
