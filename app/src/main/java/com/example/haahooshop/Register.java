package com.example.haahooshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haahooshop.utils.Global;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText shopname,owner,gstno,phone,email,password,distance;
    EditText address;
    TextView submit,show,hide;
    String device_id = null;
    Spinner spinner;
    public String idsp;
    ArrayList<String> areas = new ArrayList<String>();
    ArrayList<String> areasid = new ArrayList<String>();
    boolean doubleBackToExitPressedOnce = false;

    String URL="https://testapi.creopedia.com/api_shop_app/list_shop_cat/ ";
    Context context=this;
    private String URLline = Global.BASE_URL+"api_shop_app/shop_registeration/";
    public String source_lat,source_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        shopname=findViewById(R.id.shopname);
        owner=findViewById(R.id.owner);
        gstno=findViewById(R.id.gst);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        address=findViewById(R.id.des);
        distance=findViewById(R.id.distance);
        show=findViewById(R.id.show);
        hide=findViewById(R.id.hide);
        device_id =  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address.getText().toString(),
                        getApplicationContext(), new Register.GeocoderHandler());
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.GONE);

            }
        });

        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                hide.setVisibility(View.GONE);
                show.setVisibility(View.VISIBLE);
            }
        });




        spinner = findViewById(R.id.spinner);
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country= spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                idsp=areasid.get(spinner.getSelectedItemPosition());
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              register();
            }
        });
    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            String lat,lonh;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    lat= bundle.getString("lat");
                    lonh=bundle.getString("long");
                    Log.d("source","mm"+lat);
                    Log.d("longitude","mm"+lonh);
                    source_lat = lat;
                    source_lng = lonh;
                    Toast.makeText(Register.this,source_lat+source_lng,Toast.LENGTH_SHORT).show();
                    //  sessionManager.setDestLong(lonh);
                    break;
                default:
                    locationAddress = null;
            }
            //  latLongTV.setText(locationAddress);
            Log.d("locationaddress","mm"+locationAddress);
        }

    }


    private void loadSpinnerData(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String country=jsonObject1.getString("name");
                            String id=jsonObject1.getString("id");
                            areas.add(country);
                            areasid.add(id);

                        }

                    spinner.setAdapter(new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_dropdown_item, areas));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }


    private void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // dialog.dismiss();
                        //  Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");
                            String token=jsonObject.optString("Token");
                            //    sessionManager.setTokens(token);




                            Log.d("otp","mm"+token);
                            Log.d("code","mm"+status);
                            if(status.equals("200")){
                                Toast.makeText(Register.this, "Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, MainUI.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Register.this, "Failed."+ot, Toast.LENGTH_LONG).show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //   Log.d("response","hhh"+response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("device_id",device_id);
                Log.d("name","mm"+device_id);
                params.put("name",shopname.getText().toString());
                Log.d("name","mm"+shopname.getText().toString());
                params.put("owner_name",owner.getText().toString());
                Log.d("owener","mm"+owner.getText().toString());
                params.put("gst_no",gstno.getText().toString());
                Log.d("gstno","mm"+gstno.getText().toString());
                params.put("email",email.getText().toString());
                Log.d("email","mm"+email.getText().toString());
                params.put("password",password.getText().toString());
                Log.d("pass","mm"+password.getText().toString());
                params.put("phone_no",phone.getText().toString());
                Log.d("phone","mm"+phone.getText().toString());
                params.put("address",address.getText().toString());
                Log.d("address","mm"+address.getText().toString());
                params.put("lat",source_lat);
                Log.d("lat","mm"+source_lat);
                params.put("log",source_lng);
                Log.d("long","mm"+source_lng);
                params.put("distance",distance.getText().toString());
                Log.d("long","mm"+distance.getText().toString());
                params.put("category",idsp);
                Log.d("category","mm"+idsp);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(Register.this,"Press again to exit",Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
