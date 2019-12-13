package com.example.haahooshop;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

public class Customad extends ArrayAdapter<RowItem> {

    Context context;
    public String id;
    SessionManager sessionManager;

    public Customad(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;

        sessionManager=new SessionManager(context);

    }

    /*private view holder class*/
    private class ViewHolder {

        TextView txtTitle;
        TextView txtDesc;
        ImageView imageView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_new, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getTitle());
        id=rowItem.getId();
      /*  sessionManager.setshopid(rowItem.getId());
        Log.d("dasdasd","mm"+sessionManager.getshopid());*/



        return convertView;
    }

}