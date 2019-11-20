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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class editcategory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Specpojo> specpojos = new ArrayList<>();
    Specad specad;
    Context context = this;
    String[] value = null;
    SessionManager sessionManager;
    ImageView imageView3;
    public String ids,catid,display,Memory;
    BroadcastReceiver broadcastReceiver;
    TextView save;
    String url = "https://testapi.creopedia.com/api_shop_app/list_pdt_cat_spec/";
    private String URLline = Global.BASE_URL+"api_shop_app/pdt_spec_edit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.recycle);
        imageView3=findViewById(R.id.imageView3);
        save=findViewById(R.id.save);
        sessionManager = new SessionManager(this);
        sessionManager.setcatName("");
        Bundle bundle= getIntent().getExtras();
        ids=bundle.getString("id");
        catid=bundle.getString("catid");
        display=bundle.getString("display");
        Memory=bundle.getString("memory");

        sessionManager.setdisplay(display);
        sessionManager.setmemory(Memory);

        submituser();




        Log.d("VVGHHHBH","LLL"+ids);
        final String category = bundle.getString("category");
       // loadspecs(category);

        ArrayList<String> vals = new ArrayList<>();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddProduct.class));
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(context,"kjnkj"+ Global.category,Toast.LENGTH_SHORT).show();

//                if(Global.category.equals("Please Select ...")){
//                    Toast.makeText(context,"Please Select All Specifications"+ Global.category,Toast.LENGTH_SHORT).show();
//                }
//                if(!(Global.category.equals("Please Select ..."))){
//                    //Toast.makeText(context,"Please Select All Specifications"+ Global.category,Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(context,addprod.class));
//                }
                //Toast.makeText(context,"y"+sessionManager.getcatName(),Toast.LENGTH_SHORT).show();
                if (sessionManager.getcatName().length()==0){
                    Toast.makeText(context,"Specifications cannot be left empty",Toast.LENGTH_SHORT).show();
                }
                if (sessionManager.getcatName().length()!=0){
                    submitpt();
                }
                //
            }
        });



    }

    private void submitpt(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLline,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // dialog.dismiss();
                        // Toast.makeText(ManualOrders.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("code");
                            String id=jsonObject.optString("data");
                            sessionManager.setID(id);
                            Log.d("otp","mm"+ot);
                            if(status.equals("200")){
                                Toast.makeText(editcategory.this, "Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(editcategory.this, MainUI.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(editcategory.this, "Failed."+ot, Toast.LENGTH_LONG).show();


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
                        Toast.makeText(editcategory.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("pdt_spec",sessionManager.getcatName());
                params.put("id",ids);
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
    private void submituser(){
        RequestQueue queue = Volley.newRequestQueue(editcategory.this);

        //this is the url where you want to send the request

        String url = Global.BASE_URL+"api_shop_app/product_id/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //dialog.dismiss();

                        try {

                            JSONObject obj = new JSONObject(response);
                            Log.d("objfdfddf","hjbhjb"+response);
                            // amount.setText(obj.optString("total"));
                         //   birdList = new ArrayList<>();
                            String total=obj.optString("total");
                            JSONArray dataArray  = obj.getJSONArray("data");


                            for (int i = 0; i < dataArray.length(); i++) {

                                //Item playerModel = new Item();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Global.spec_headers.clear();
                                Global.spec_values.clear();
                                //   playerModel.setProductname(dataobj.optSt ring("name"));
                                // ApiClient.productids.add(dataobj.optString("id"));
                               /* pname=dataobj.optString("name");
                                price=dataobj.optString("price");
                                descr=dataobj.optString("description");
                                discount=dataobj.optString("discount");*/
                                ArrayList<String>spec_headers = new ArrayList<>();
                                JSONObject specification_headers = dataobj.optJSONObject("specification_header");
                                for (int j = 0 ; j < specification_headers.length() ; j++){
                                    Global.spec_headers.add(specification_headers.optString("spec"+j));
                                    //Log.d("specheaders","bhjb"+spec_headers.get(j));
                                }
                                ArrayList<String>spec_values = new ArrayList<>();
                                JSONObject specifications = dataobj.optJSONObject("specification");
                                for (int k = 0 ; k <Global.spec_headers.size();k++){
                                    Global.spec_values.add(specifications.optString(Global.spec_headers.get(k)));
                                    //Log.d("specvalues","bhjb"+spec_values.get(k));
                                }
//                                Global.spec_headers = spec_headers;
//                                Global.spec_values = spec_values;
                              /*  stock=dataobj.optString("stock");
                                //  email=dataobj.optString("email");
                                String id=dataobj.getString("id");
                                Log.d("imageurl","bhcbvfc"+id);
                                playerModel.setId(id);
                                sessionManager.setPdtid(id);
                                String catid=dataobj.optString("category_id");
                                playerModel.setCategoryid(catid);
                                playerModel.setDescription(descr);
                                playerModel.setDiscount(discount);
                                playerModel.setStock(stock);*/
                                //    playerModel.setEmail(email);
                              /*  playerModel.setName(dataobj.optString("name"));
                                Log.d("ssssd", "resp" + dataobj);
                                playerModel.setPrice("₹ "+dataobj.optString("price"));
                                String images1 = dataobj.getString("image");
                                String[] seperated = images1.split(",");
                                String split = seperated[0].replace("[", "").replace("]","");
                                playerModel.setImage(Global.BASE_URL+split);
                                image=Global.BASE_URL+split;*/

                               /* JSONObject jsonArray=dataobj.optJSONObject("specifications");
                                Log.d("specifications","mm"+jsonArray);
                                String display=jsonArray.optString("Display");
                                Log.d("specifications","mm"+display);
                                String Memory=jsonArray.optString("Memory");
                                Log.d("specifications","mm"+Memory);

                                playerModel.setDisplay(display);
                                playerModel.setMemory(Memory);*/
                                /*   JSONObject jsonObject=jsonArray.getJSONObject(0);



                                 */



                                //  images.add(split);
                                //  playerModel.setStatus("");


                               // birdList.add(playerModel);

                            }
                            specad = new Specad(Global.spec_headers,context);
                            recyclerView.setAdapter(specad);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                           /* MyAdapter myAdapter=new MyAdapter(context,R.layout.grid_view_items,birdList);
                            simpleList.setAdapter(myAdapter);
*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editcategory.this,"Internal Server Error",Toast.LENGTH_LONG).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("product_id", ids);
                return params;
            }

            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token " + sessionManager.getTokens());
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

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
                                //specad = new Specad(specpojos, context);
                                recyclerView.setAdapter(specad);
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
                        Toast.makeText(editcategory.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("cat_id",catid);

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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context,AddProduct.class));
    }
}
