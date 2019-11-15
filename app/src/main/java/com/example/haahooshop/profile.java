package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {
    ImageView imageView;
    TextView shopname,location,gstno,catgory,owner;
    private String URLline = Global.BASE_URL+"api_shop_app/shop_details_show/";
    SessionManager sessionManager;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sessionManager=new SessionManager(this);

        imageView=findViewById(R.id.img);
        shopname=findViewById(R.id.sname);
        location=findViewById(R.id.location);
        owner=findViewById(R.id.owner);
        gstno=findViewById(R.id.gstno);
        catgory=findViewById(R.id.category);


        submituser();

    }

    private void submituser(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     //   dialog.dismiss();
                        //  Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");

                            JSONArray jsonArray=jsonObject.optJSONArray("data");
                            JSONObject jsonObject1 = jsonArray.optJSONObject(0);

                            String shopname=jsonObject1.optString("name");
                            String location=jsonObject1.optString("location");
                            String owner=jsonObject1.optString("owner");
                            String gst_no=jsonObject1.optString("gst_no");
                            String category=jsonObject1.optString("category");
                            String images1 = jsonObject1.getString("image");
                            String[] seperated = images1.split(",");
                            String split = seperated[0].replace("[", "").replace("]","");






                            Log.d("code","mm"+status);
                            if(status.equals("200")){
                                Toast.makeText(profile.this, "Successful", Toast.LENGTH_LONG).show();
                               // Intent intent = new Intent(pasteaddress.this, ordersummary.class);
                               // startActivity(intent);
                            }
                            else{
                                Toast.makeText(profile.this, "Failed."+ot, Toast.LENGTH_LONG).show();


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
                        Toast.makeText(profile.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token "+sessionManager.getTokens());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }
}
