package com.example.haahooshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;
import com.ncorti.slidetoact.SlideToActView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Imagepojo> dataModelArrayList;
    public Context context1 ;
    public String status="0";
    public String stat="0";
    public String id;
    private String URLline = Global.BASE_URL+"virtual_order_management/shop_order_accpt/";
    SessionManager sessionManager;

    public ImageAdapter(Context ctx, ArrayList<Imagepojo> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.context1 = ctx;
        this.dataModelArrayList = dataModelArrayList;
        sessionManager=new SessionManager(context1);
    }

    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.image_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.MyViewHolder holder, final int position) {


        //Picasso.with(context1).load(dataModelArrayList.get(position).getImage()).into(holder.iv);

Log.d("yugyug","yghfghf"+Global.imj[position]);
            String split = Global.imj[position].replace("[", "").replace("]","").trim();
            Picasso.get().load(Global.BASE_URL+split).into(holder.iv);




    }

    @Override
    public int getItemCount() {
        return Global.imj.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView iv;


        public MyViewHolder(View itemView) {
            super(itemView);


            iv = itemView.findViewById(R.id.imgg);


//            qty = (TextView) itemView.findViewById(R.id.non);
        }

    }

    /*private void accept(){

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
                            if(status.equals("200")&&(!(ot.equals("verify")))){
                                Toast.makeText(context1, "Successful", Toast.LENGTH_LONG).show();



                            }


                            if(!(status.equals("200"))){
                                Toast.makeText(context1, "Failed."+ot, Toast.LENGTH_LONG).show();


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
                        Toast.makeText(context1,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("order_id",id);
                params.put("accepted",status);
                Log.d("idlllll","mm"+status);

                return params;
            }

            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token " + sessionManager.getTokens());
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context1);
        requestQueue.add(stringRequest);

    }*/


}