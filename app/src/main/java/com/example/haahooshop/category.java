package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class category extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Specpojo> specpojos = new ArrayList<>();
    SpecAdapter specAdapter;
    Context context = this;
    String[] value = null;
    SessionManager sessionManager;
    BroadcastReceiver broadcastReceiver;
    TextView save;
    String url = "https://testapi.creopedia.com/api_shop_app/list_pdt_cat_spec/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.recycle);
        save=findViewById(R.id.save);
        sessionManager = new SessionManager(this);
        Bundle bundle =getIntent().getExtras();
        final String category = bundle.getString("category");
        loadspecs(category);
        ArrayList<String> vals = new ArrayList<>();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context,"kjnkj"+ Global.category,Toast.LENGTH_SHORT).show();

                if(Global.category.equals("Please Select ...")){
                    Toast.makeText(context,"Please Select All Specifications"+ Global.category,Toast.LENGTH_SHORT).show();
                }
                if(!(Global.category.equals("Please Select ..."))){
                    //Toast.makeText(context,"Please Select All Specifications"+ Global.category,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context,addprod.class));
                }
              //
            }
        });



    }


    private void loadspecs(final String category){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //    dialog.dismiss();
                        //  Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String message=jsonObject.optString("message");

                            if(message.equals("No Specifications")){
                                Toast.makeText(context,"Currently no specifications available for the choosen category",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(context,addprod.class));
                            }
                            if(message.equals("success")){



                            JSONArray jsonArray = jsonObject.optJSONArray("data");
                            for (int i =0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                Specpojo specpojo = new Specpojo();
                                specpojo.setName(jsonObject1.optString("name"));
                                specpojo.setId(jsonObject1.optString("id"));
                                value = jsonObject1.optString("values").split(",");
                                ArrayList<String> values = new ArrayList<>();
                                values.add("Please Select ...");
                                for (int j = 0 ;j<value.length;j++){
                                    values.add(value[j].replace("[","").replace("]",""));
                                }
                                specpojo.setValues(values);
                               // Log.d("jsonresponse","hgf"+value[0]+"kkk"+value[1]+value.length);
                                specpojos.add(specpojo);
                            }
                            specAdapter = new SpecAdapter(specpojos, context);
                            recyclerView.setAdapter(specAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
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
                        Toast.makeText(category.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("cat_id",category);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization","Token "+sessionManager.getTokens());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
