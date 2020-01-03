package com.example.haahooshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class choosepdtcategory extends AppCompatActivity {

    private RecyclerView offerRecyclerView;
    Activity activity=this;
    ImageView imageView3;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepdtcategory);

        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.black));

        imageView3=findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Navigation.class));
            }
        });

        offerRecyclerView = (RecyclerView) findViewById(R.id.offers_lst);


        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        offerRecyclerView.setLayoutManager(recyclerLayoutManager);

     /*   DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(offerRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        offerRecyclerView.addItemDecoration(dividerItemDecoration);*/


        choosepdtcategoryadapter recyclerViewAdapter = new
                choosepdtcategoryadapter(getBrands(),this);
        offerRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<OffersModel> getBrands(){
        List<OffersModel> modelList = new ArrayList<OffersModel>();
        modelList.add(new OffersModel("Get Upto 20% Off Clothing"));
        modelList.add(new OffersModel("Free Smart Phone"));
        modelList.add(new OffersModel("Pay $100 get big HD TV"));

        return modelList;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context,Navigation.class));
    }
}
