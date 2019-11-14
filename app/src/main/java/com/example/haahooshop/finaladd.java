package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class finaladd extends AppCompatActivity {
    TextInputEditText distance;
    Spinner spinner;
    ArrayList<String> areas = new ArrayList<String>();
    String delivery_type = "null";
    String URL="https://testapi.creopedia.com/api_shop_app/list_shop_cat/ ";
    CheckBox checkBox1,checkBox2,checkBox3;
    private RadioGroup radioSexGroup;
    private RadioButton one,two,three;

    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaladd);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox1);
        checkBox3 = findViewById(R.id.checkBox2);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        one=findViewById(R.id.radioMale);
        two=findViewById(R.id.radioFemale);
        three=findViewById(R.id.radioFe);
        save=findViewById(R.id.save);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    delivery_type = checkBox1.getText().toString();
                    Toast.makeText(finaladd.this,"bhnjv"+checkBox1.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    delivery_type = checkBox2.getText().toString();
                    Toast.makeText(finaladd.this,"bhnjv"+checkBox2.getText().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    delivery_type = checkBox3.getText().toString();
                    Toast.makeText(finaladd.this,"bhnjv"+checkBox3.getText().toString(),Toast.LENGTH_SHORT).show();

                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                one = (RadioButton) findViewById(selectedId);

                Toast.makeText(MyAndroidAppActivity.this,
                        radioSexButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

//        if (checkBox1.isChecked()){
//            delivery_type = checkBox1.getText().toString();
//            Toast.makeText(finaladd.this,"hjvgjh"+delivery_type,Toast.LENGTH_SHORT).show();
//            checkBox2.setChecked(false);
//            checkBox3.setChecked(false);
//        }
//        if (checkBox2.isChecked()){
//            delivery_type = checkBox2.getText().toString();
//            Toast.makeText(finaladd.this,"hjvgjh"+delivery_type,Toast.LENGTH_SHORT).show();
//            checkBox1.setChecked(false);
//            checkBox3.setChecked(false);
//        }
//        if (checkBox3.isChecked()){
//            delivery_type = checkBox3.getText().toString();
//            Toast.makeText(finaladd.this,"hjvgjh"+delivery_type,Toast.LENGTH_SHORT).show();
//            checkBox1.setChecked(false);
//            checkBox2.setChecked(false);
//        }
        distance=findViewById(R.id.name);

       // spinner = findViewById(R.id.spinner);
       // loadSpinnerData(URL);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String country= spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
//              //  idsp=areasid.get(spinner.getSelectedItemPosition());
//                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                // DO Nothing here
//            }
//        });

    }



    private void loadSpinnerData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
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
                      //  areasid.add(id);

                    }

                    spinner.setAdapter(new ArrayAdapter<String>(finaladd.this, android.R.layout.simple_spinner_dropdown_item, areas));
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


}
