package com.example.haahooshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecAdapter extends RecyclerView.Adapter<SpecAdapter.ViewHolder> {

    public ArrayList<Specpojo> downloadPojos;
    Context context1 ;
    JSONArray arr = new JSONArray();
    JSONObject products = new JSONObject();
    SessionManager sessionManager;

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
    public void onBindViewHolder(final SpecAdapter.ViewHolder holder, final int position) {
        sessionManager = new SessionManager(context1);
        holder.spec1.setText(downloadPojos.get(position).getName());
        holder.spinner.setAdapter(new ArrayAdapter<String>(context1, android.R.layout.simple_spinner_dropdown_item, downloadPojos.get(position).getValues()));
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> names = new ArrayList<>();
               // names.clear();
                names.add(downloadPojos.get(position).getName());

                ArrayList<String> values = new ArrayList<>();
                values.clear();
                values.add(downloadPojos.get(position).getValues().get(holder.spinner.getSelectedItemPosition()));
                HashMap<String, JSONObject> map = new HashMap<String, JSONObject>();
               // Toast.makeText(context1,""+Global.specpojos.size(),Toast.LENGTH_SHORT).show();
              //  for (int j = 0; j < 1; j++) {
                Global.category = downloadPojos.get(position).getValues().get(holder.spinner.getSelectedItemPosition());
                    if(!(downloadPojos.get(position).getValues().get(holder.spinner.getSelectedItemPosition()).equals("Please Select ..."))){
                        HashMap<String,JSONObject> map1 = new HashMap<String, JSONObject>();
                        JSONObject json = new JSONObject();
                        try {

                            json.put("name",names.get(0));
                            json.put("value",values.get(0));
                            map1.put("json",json);
                            arr.put(map1.get("json"));
                     //       products.put("product",arr);

//                            JSONArray jsonArray = new JSONArray(json.toString());
//                            JSONObject jsonObject = new JSONObject();
//                            jsonObject.put("products",jsonArray.toString());

//                            map.put("json" +0, json);
//                            arr.put(map.get("json" + 0));
//
//                            //arr.put(map.get("json" + j));
//
//                                products.put("product", arr);
                            if (arr.length()>2){
                                arr.remove(0);
                            }
                            products.put("spec",arr);
                            sessionManager.setcatName(products.toString());


                                Log.d("fff", "mm" +sessionManager.getcatName());
                            Log.d("fff", "mm" +arr);
                            Log.d("fff", "mm" +products);
                            //Log.d("sizedfgdfgfg11", "mm" + arr.getJSONObject(0).getString("name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                //}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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