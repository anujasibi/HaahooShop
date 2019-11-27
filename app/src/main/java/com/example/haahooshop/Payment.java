package com.example.haahooshop;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.haahooshop.utils.SessionManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment extends Activity implements PaymentResultListener {
    private static final String TAG = Payment.class.getSimpleName();
    private String number,email;
    String razorid="null";
    private String URLlin = "https://testapi.creopedia.com/api_shop_app/shop_payment/";
    SessionManager sessionManager;
    Context context=this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getIntent().getExtras();
        email=bundle.getString("email");
        number=bundle.getString("number");

        setContentView(R.layout.activity_payment);
        sessionManager=new SessionManager(this);

        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Haahoo Shop");
            options.put("description", "Registration Fee");
            //You can omit the image option to fetch the image from dashboard

            options.put("currency", "INR");
            options.put("amount", "100000");

            JSONObject preFill = new JSONObject();
            preFill.put("email", email);
            preFill.put("contact",number);

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            Log.d("paymentidmmmm","mm"+razorpayPaymentID);

            razorid=razorpayPaymentID;
            boouser();

        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    private void boouser(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLlin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  dialog.dismiss();
                      //  Toast.makeText(Payment.this,response,Toast.LENGTH_LONG).show();
                        //parseData(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String ot = jsonObject.optString("message");
                            String status=jsonObject.optString("status");
                            String code=jsonObject.optString("code");

                          //  Log.d("otp","mm"+ot);
                            if(code.equals("200")) {
                                Toast.makeText(Payment.this, ot, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Payment.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(Payment.this, ot, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                      //  Log.d("response","hhh"+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Payment.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("shop_id", sessionManager.getpayid());
                Log.d("driver","mm"+sessionManager.getpayid());
                params.put("payment_amount","100000");
                params.put("payment_id",razorid);
                Log.d("paymentid",razorid);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Token "+sessionManager.getTokens());
                Log.d("paymentid",sessionManager.getTokens());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}