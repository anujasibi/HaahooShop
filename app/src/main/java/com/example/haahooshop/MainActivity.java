package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextInputLayout pwdlayout;
    TextInputEditText phoneno,password;
    TextView continuetologin,login;
    FirebaseAnalytics firebaseAnalytics;
    private String token_firebase;
    Context context=this;
    String device_id = null;
    private ProgressDialog dialog ;
    Activity activity = this;
    SessionManager sessionManager;
    TextView forgot;
    private String URLline = Global.BASE_URL+"api_shop_app/shop_otp_generation/";
    private String URLli = Global.BASE_URL+"api_shop_app/shop_login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.black));

        sessionManager = new SessionManager(this);
        forgot=findViewById(R.id.forgot);
        phoneno=findViewById(R.id.uname);
        pwdlayout=findViewById(R.id.pwdlayout);
        password=findViewById(R.id.pwd);
        login=findViewById(R.id.login);
        continuetologin=findViewById(R.id.continuetologin);
        device_id =  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        token_firebase = FirebaseInstanceId.getInstance().getToken();
        Log.d("tokkkken","lhykhiyh"+token_firebase);
        dialog=new ProgressDialog(MainActivity.this,R.style.MyAlertDialogStyle);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,ForgotPassword.class));
            }
        });

        continuetologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Loading");
                dialog.show();
                submit();
            }
        });
    }
    private void submit(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       dialog.dismiss();
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
                            if(status.equals("200")&&(!(ot.equals("verify")))){
                                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, VerifyOtp.class);
                                intent.putExtra("phone_no",phoneno.getText().toString());
                                intent.putExtra("otp",ot);
                                startActivity(intent);
                            }
                            if(ot.equals("verify")){
                                password.setVisibility(View.VISIBLE);
                                pwdlayout.setVisibility(View.VISIBLE);
                                login.setVisibility(View.VISIBLE);
                                forgot.setVisibility(View.VISIBLE);
                                continuetologin.setVisibility(View.GONE);

                                login.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        loginuser();
                                    }
                                });

                            }
                            if(!(status.equals("200"))){
                                Toast.makeText(MainActivity.this, "Failed."+ot, Toast.LENGTH_LONG).show();


                            }

                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                          Log.d("response","hhh"+response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("phone_no",phoneno.getText().toString());

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
    private void loginuser(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLli,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    //    dialog.dismiss();
                        //  Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");
                            String token=jsonObject.optString("Token");
                         sessionManager.setTokens(token);




                            Log.d("otp","mm"+token);
                            Log.d("code","mm"+status);
                            if(status.equals("200")){
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, MainUI.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Login Failed."+ot, Toast.LENGTH_LONG).show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                          Log.d("response","hhh"+response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("phone_no",phoneno.getText().toString());
                params.put("password",password.getText().toString());
                params.put("fire_token",token_firebase);
                params.put("device_id",device_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(context,"Press again to exit",Toast.LENGTH_SHORT).show();
    }
}
