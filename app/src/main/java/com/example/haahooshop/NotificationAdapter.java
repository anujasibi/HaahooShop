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


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<NotificationPojo> dataModelArrayList;
    public Context context1 ;
    public String id;
    SessionManager sessionManager;

    public NotificationAdapter(Context ctx, ArrayList<NotificationPojo> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.context1 = ctx;
        this.dataModelArrayList = dataModelArrayList;
        sessionManager=new SessionManager(context1);
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.notificationrow, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.MyViewHolder holder, final int position) {


        //  holder.statuss.setText(dataModelArrayList.get(position).getStatus());



    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView notific,noi;


        public MyViewHolder(View itemView) {
            super(itemView);

            notific = (TextView) itemView.findViewById(R.id.title);
            noi=(TextView) itemView.findViewById(R.id.titles);

//
        }

    }




}