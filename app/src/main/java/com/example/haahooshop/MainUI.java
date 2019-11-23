package com.example.haahooshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.haahooshop.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MainUI extends AppCompatActivity {

    ImageView imageView;
    boolean doubleBackToExitPressedOnce = false;
    CardView cardView;
    Activity activity = this;
    ImageView logout;
    SessionManager sessionManager;

    private List<CardRecyclerViewItem> carItemList = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

      //  imageView=findViewById(R.id.);
       sessionManager = new SessionManager(this);

        cardView=findViewById(R.id.card);
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.black));




        initializeCarItemList();

        // Create the recyclerview.
        RecyclerView carRecyclerView = (RecyclerView)findViewById(R.id.card_view_recycler_list);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        carRecyclerView.setLayoutManager(gridLayoutManager);

        // Create car recycler view data adapter with car item list.
        RecyclerViewDataAdapter carDataAdapter = new RecyclerViewDataAdapter(carItemList);
        // Set data adapter.
        carRecyclerView.setAdapter(carDataAdapter);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setTokens(null);
                startActivity(new Intent(MainUI.this, MainActivity.class));
                finish();


            }
        });






    }

    /* Initialise car items in list. */
    private void initializeCarItemList()
    {
        if(carItemList == null)
        {
            carItemList = new ArrayList<CardRecyclerViewItem>();
            carItemList.add(new CardRecyclerViewItem("Add Products", R.drawable.add));
            carItemList.add(new CardRecyclerViewItem("View/Modify Products", R.drawable.eye));
            carItemList.add(new CardRecyclerViewItem("View/Modify Profile", R.drawable.person));
            carItemList.add(new CardRecyclerViewItem("Add Your Employee", R.drawable.groupadd));
            carItemList.add(new CardRecyclerViewItem("View Employee Details", R.drawable.viewemp));
            carItemList.add(new CardRecyclerViewItem("Add Your Shop Branches", R.drawable.shopbranch));
            carItemList.add(new CardRecyclerViewItem("My Branches", R.drawable.branch));
            carItemList.add(new CardRecyclerViewItem("View Branch Products", R.drawable.view));
            carItemList.add(new CardRecyclerViewItem("View Upcoming Orders", R.drawable.ordersu));




        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(MainUI.this,"Press again to exit",Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}