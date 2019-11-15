package com.example.haahooshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<Item> birdList = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        birdList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        TextView textView1=(TextView)v.findViewById(R.id.textView1);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        textView.setText(birdList.get(position).getName());
        Picasso.with(getContext()).load(birdList.get(position).getImage()).into(imageView);
        textView1.setText(birdList.get(position).getPrice());
        return v;

    }

}