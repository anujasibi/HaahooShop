package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class editbranchprofile extends AppCompatActivity {
    EditText shopname,owner,gst,email;
    TextView submit;
    private String URLline = Global.BASE_URL+"api_shop_app/branch_update/";
    SessionManager sessionManager;
    Context context=this;
    ImageView back;
    private ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbranchprofile);

        shopname=findViewById(R.id.shopname);
        gst=findViewById(R.id.gst);
        email=findViewById(R.id.email);





        shopname.setText(Global.name);
     gst.setText(Global.gst);
        email.setText(Global.email);

        dialog=new ProgressDialog(editbranchprofile.this,R.style.MyAlertDialogStyle);

        sessionManager=new SessionManager(this);

        submit=findViewById(R.id.submit);

        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Loading..");
                dialog.show();
                submituser();

            }
        });

    }
    private void submituser(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        // Toast.makeText(ManualOrders.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");
                            //  String id=jsonObject.optString("data");
                            Log.d("otp","mm"+ot);
                            if(status.equals("200")){
                                Toast.makeText(editbranchprofile.this, "Successfully Updated", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(editbranchprofile.this,branchprofile.class));
                            }
                            else{
                                Toast.makeText(editbranchprofile.this, "Failed."+ot, Toast.LENGTH_LONG).show();


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
                        Toast.makeText(editbranchprofile.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",shopname.getText().toString());
                params.put("owner_name","");
                params.put("gst_no",gst.getText().toString());
                params.put("email",email.getText().toString());
                params.put("id",Global.id);
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
