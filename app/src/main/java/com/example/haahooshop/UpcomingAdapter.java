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

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<upcomingrow> dataModelArrayList;
    public Context context1 ;

    public UpcomingAdapter(Context ctx, ArrayList<upcomingrow> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.context1 = ctx;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public UpcomingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.upcominglist, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(UpcomingAdapter.MyViewHolder holder, final int position) {

        Picasso.with(context1).load(dataModelArrayList.get(position).getImage()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getName());
       holder.location.setText(dataModelArrayList.get(position).getLocation());
     //   Picasso.with(context1).load(dataModelArrayList.get(position).getQty()).into(holder.iv);
       /* holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1,Orderdetails.class);
                intent.putExtra("order_id",dataModelArrayList.get(position).getProductid());
                ApiClient.sh_order_id = dataModelArrayList.get(position).getShid();
                intent.putExtra("virtual",ApiClient.virtual_order.get(position));
                context1.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, location;
       // LinearLayout linearLayout;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            location = (TextView) itemView.findViewById(R.id.desc);
            iv = itemView.findViewById(R.id.profile_image);
//            qty = (TextView) itemView.findViewById(R.id.non);
        }

    }
}