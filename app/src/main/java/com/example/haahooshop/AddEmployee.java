package com.example.haahooshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddEmployee extends AppCompatActivity {
    EditText empname,branch,phone,password;
    TextView submit,show,hide;
    private String Urline = Global.BASE_URL+"api_shop_app/employee_registration/";
    Context context=this;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        empname=findViewById(R.id.shopname);
        branch=findViewById(R.id.distance);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);

        show = findViewById(R.id.show);
        hide = findViewById(R.id.hide);

        sessionManager=new SessionManager(this);

        submit = findViewById(R.id.submit);

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


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submituser();
            }
        });
    }

    private void submituser(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urline, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ot = jsonObject.optString("message");
                    String status=jsonObject.optString("code");
                    String token=jsonObject.optString("Token");
                    //    sessionManager.setTokens(token);




                    Log.d("otp","mm"+token);
                    Log.d("code","mm"+status);
                    if(status.equals("200")){
                        Toast.makeText(AddEmployee.this, "Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddEmployee.this, MainUI.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(AddEmployee.this, "Failed."+ot, Toast.LENGTH_LONG).show();


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
                        Toast.makeText(AddEmployee.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
               // params.put("device_id",device_id);
              //  Log.d("name","mm"+device_id);
                params.put("name",empname.getText().toString());
                Log.d("name","mm"+empname.getText().toString());
                params.put("branch_name",branch.getText().toString());
                Log.d("owener","mm"+branch.getText().toString());
                params.put("password",password.getText().toString());
                Log.d("pass","mm"+password.getText().toString());
                params.put("phone",phone.getText().toString());
                Log.d("phone","mm"+phone.getText().toString());


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token "+sessionManager.getTokens());
                Log.d("token","mm"+sessionManager.getTokens());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
