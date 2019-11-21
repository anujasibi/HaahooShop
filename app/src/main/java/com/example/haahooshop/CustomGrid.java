package com.example.haahooshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haahooshop.utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomGrid extends ArrayAdapter {
    ArrayList<Itemprod> birdList = new ArrayList<>();
    private Context mContext;

    public CustomGrid(Context context, int textViewResourceId, ArrayList<Itemprod> objects) {
        super(context, textViewResourceId, objects);
        birdList = objects;
       // sessionManager=new SessionManager(getContext());
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub

            return super.getCount();
    }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridviewadd, null);
            TextView textView = (TextView) grid.findViewById(R.id.textView);
            ImageView imageView = (ImageView)grid.findViewById(R.id.profile_image);
            textView.setText(birdList.get(position).getName());
            Picasso.with(getContext()).load(birdList.get(position).getImage()).into(imageView);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}