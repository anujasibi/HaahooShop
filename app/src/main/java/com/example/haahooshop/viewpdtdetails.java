package com.example.haahooshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class viewpdtdetails extends AppCompatActivity {
    ImageView imageView,image,io;
    Context context = this;
    TextView shopname,location,gstno,catgory,owner,edit;
    String id = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // will hide the title
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdtdetails);

        imageView=findViewById(R.id.img);
        shopname=findViewById(R.id.sname);
        location=findViewById(R.id.location);
        owner=findViewById(R.id.owner);
        gstno=findViewById(R.id.gstno);
        catgory=findViewById(R.id.category);
        edit=findViewById(R.id.edit);
        io=findViewById(R.id.io);

        io.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

//Extract the data…
        final String pname = bundle.getString("pname");
        final String image = bundle.getString("image");
        Picasso.with(context).load(image).into(imageView);
        final String price = bundle.getString("price");
        final String stock = bundle.getString("stock");
        final String discount = bundle.getString("discount");
        final String des = bundle.getString("des");
        id=bundle.getString("id");
        Log.d("mnjbkjbkbj","hjghjghg"+id);
      //  final String ema=bundle.getString("email");

        shopname.setText(pname);
        location.setText(price);
        owner.setText(discount);
        gstno.setText(stock);
        catgory.setText(des);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,edit_product.class);
                intent.putExtra("pname",pname);
                intent.putExtra("image",image);
                intent.putExtra("price",price);
                intent.putExtra("stock",stock);
                intent.putExtra("discount",discount);
                intent.putExtra("desc",des);
                intent.putExtra("id",id);

             //   intent.putExtra("email",ema);
                startActivity(intent);

                //startActivity(new Intent(viewpdtdetails.this,edit_product.class));
            }
        });

    }
}
