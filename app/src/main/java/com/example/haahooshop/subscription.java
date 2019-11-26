package com.example.haahooshop;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haahooshop.utils.Global;
import com.example.haahooshop.utils.SessionManager;

import java.util.ArrayList;

public class subscription extends AppCompatActivity {
    CheckBox check,checkm;

    RelativeLayout re;
    TextView save;
    private String status="0";
    private String sub="null";
    SessionManager sessionManager;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    private String delivery_type;
    ArrayList<String>value=new ArrayList<>();
    TextView yp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        check=findViewById(R.id.checkBo);
        checkm=findViewById(R.id.checkBo1);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox1);
        checkBox3 = findViewById(R.id.checkBox2);
        checkBox4=findViewById(R.id.checkBox3);
        yp=findViewById(R.id.yp);
        Global.value.clear();

        re=findViewById(R.id.ret);
        sessionManager=new SessionManager(this);
        save=findViewById(R.id.save);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkm.setChecked(false);
                status="1";
                yp.setVisibility(View.VISIBLE);
                checkBox1.setVisibility(View.VISIBLE);
                checkBox2.setVisibility(View.VISIBLE);
                checkBox3.setVisibility(View.VISIBLE);
                checkBox4.setVisibility(View.VISIBLE);
                sub=check.getText().toString();
                sessionManager.setsub(status);
            }
        });

        checkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check.setChecked(false);
                status="0";
                sub=checkm.getText().toString();
                sessionManager.setsub(status);
                yp.setVisibility(View.GONE);
                checkBox1.setVisibility(View.GONE);
                checkBox2.setVisibility(View.GONE);
                checkBox3.setVisibility(View.GONE);
                checkBox4.setVisibility(View.GONE);
            }
        });

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    delivery_type = checkBox1.getText().toString();
                    value.add(delivery_type);
                   // sessionManager.setcheck(delivery_type);
                    Toast.makeText(subscription.this,"bhnjv"+checkBox1.getText().toString(),Toast.LENGTH_SHORT).show();
                }
                if(!(((CheckBox) view).isChecked())){
                    value.remove(delivery_type);
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    delivery_type = checkBox2.getText().toString();
                    value.add(delivery_type);
                   // sessionManager.setcheck(delivery_type);
                    Toast.makeText(subscription.this,"bhnjv"+checkBox2.getText().toString(),Toast.LENGTH_SHORT).show();

                }
                if(!(((CheckBox) view).isChecked())){
                    value.remove(delivery_type);
                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    delivery_type = checkBox3.getText().toString();
                    value.add(delivery_type);
                   // sessionManager.setcheck(delivery_type);
                    Toast.makeText(subscription.this,"bhnjv"+checkBox3.getText().toString(),Toast.LENGTH_SHORT).show();

                }
                if(!(((CheckBox) view).isChecked())){
                    value.remove(delivery_type);
                }
            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    delivery_type = checkBox4.getText().toString();
                    value.add(delivery_type);
                    // sessionManager.setcheck(delivery_type);
                    Toast.makeText(subscription.this,"bhnjv"+checkBox4.getText().toString(),Toast.LENGTH_SHORT).show();

                }
                if(!(((CheckBox) view).isChecked())){
                    value.remove(delivery_type);
                }
            }
        });

        Log.d("valuebkjbhkbhk","mm"+value);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("valuebkjbhkbhk","mm"+value);
                if(status.equals("0")){
                    startActivity(new Intent(subscription.this,addimage.class));
                }
                if(status.equals("1")){
                    Global.value=value;
                    Log.d("valueeeee","mm"+value);
                    startActivity((new Intent(subscription.this,subscriptionmode.class)));
                }
            }
        });



    }
}
