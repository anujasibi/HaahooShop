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
import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;
import com.ncorti.slidetoact.SlideToActView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<CancelPojo> dataModelArrayList;
    public Context context1 ;
    public String id;
    SessionManager sessionManager;

    public CancelAdapter(Context ctx, ArrayList<CancelPojo> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.context1 = ctx;
        this.dataModelArrayList = dataModelArrayList;
        sessionManager=new SessionManager(context1);
    }

    @Override
    public CancelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.cancellist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final CancelAdapter.MyViewHolder holder, final int position) {


        //  holder.statuss.setText(dataModelArrayList.get(position).getStatus());

        holder.name.setText(dataModelArrayList.get(position).getPdtname());
        holder.phone.setText(dataModelArrayList.get(position).getPhone());
        holder.uname.setText(dataModelArrayList.get(position).getUname());
        holder.date.setText(dataModelArrayList.get(position).getOrderdate());
        holder.reason.setText(dataModelArrayList.get(position).getReason());

        Picasso.get().load(dataModelArrayList.get(position).getImage()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView iv;
        TextView status,uname,phone,date,reason;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);

            iv = itemView.findViewById(R.id.profile_image);

            // cardView=itemView.findViewById(R.id.card);


            uname=itemView.findViewById(R.id.statuse);
            phone=itemView.findViewById(R.id.namee);
            date=itemView.findViewById(R.id.areaa);
            reason=itemView.findViewById(R.id.amountt);

//
        }

    }




}