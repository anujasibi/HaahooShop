package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.haahooshop.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;

public class addprod extends AppCompatActivity {
    TextInputEditText name,address,pincode,stock;
    EditText des;
    SessionManager sessionManager;
    Context context1=this;
    TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprod);

        sessionManager = new SessionManager(context1);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);
        stock=findViewById(R.id.stock);
        save = findViewById(R.id.save);

        des=findViewById(R.id.des);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setprice(name.getText().toString());
                Log.d("name","mm"+sessionManager.getprice());
                sessionManager.setret(address.getText().toString());
                Log.d("name","mm"+sessionManager.getret());
                sessionManager.setdis(pincode.getText().toString());
                Log.d("name","mm"+sessionManager.getdis());
                sessionManager.setstock(stock.getText().toString());
                Log.d("name","mm"+sessionManager.getstock());
                sessionManager.setdes(des.getText().toString());
                Log.d("name","mm"+sessionManager.getdes());
                startActivity(new Intent(addprod.this,finaladd.class));
            }
        });





    }
}
