package com.example.haahooshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.ViewHolder> {

    public ArrayList<Specpojo> downloadPojos;
    Context context1 ;

    public SpecAdapter(ArrayList<Specpojo> productPojo, Context context) {
        this.downloadPojos = productPojo;
        this.context1 = context;
    }

    @Override
    public SpecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.specification, parent, false);
        SpecAdapter.ViewHolder viewHolder = new SpecAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SpecAdapter.ViewHolder holder, int position) {

        holder.spec1.setText(downloadPojos.get(position).getName());
        holder.spinner.setAdapter(new ArrayAdapter<String>(context1, android.R.layout.simple_spinner_dropdown_item, downloadPojos.get(position).getValues()));


    }

    @Override
    public int getItemCount() {
        return downloadPojos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView spec1;
        public Spinner spinner;

        public ViewHolder(View itemView) {
            super(itemView);

            spec1 = itemView.findViewById(R.id.spec1);
            spinner = itemView.findViewById(R.id.spinner);

        }

    }

}