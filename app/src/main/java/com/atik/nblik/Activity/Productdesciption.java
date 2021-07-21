package com.atik.nblik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atik.nblik.R;
import com.atik.nblik.Rest.ApiClient;
import com.atik.nblik.Rest.ApiInterface;
import com.atik.nblik.Model.Productlist;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Productdesciption extends AppCompatActivity {
     TextView category,title,price,description,qty;
     ImageView image,minus,plus;
     String ID;
     LinearLayout qtylayout;
     Button cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdesciption);
        qtylayout=(LinearLayout) findViewById(R.id.qtylayout);
        category=(TextView)findViewById(R.id.category);
        title=(TextView)findViewById(R.id.title);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);
        image=(ImageView)findViewById(R.id.image);
        minus=(ImageView)findViewById(R.id.minus);
        plus=(ImageView)findViewById(R.id.plus);
        qty=(TextView)findViewById(R.id.qty);
        cart=(Button) findViewById(R.id.cart);
        ID=getIntent().getStringExtra("id");
        cart.setVisibility(View.GONE);
        qtylayout.setVisibility(View.GONE);
        Productdetails(ID);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Productdesciption.this,Cart.class);
                startActivity(intent); }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(qty.getText()));
                count++;
                qty.setText("" + count);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(qty.getText()));
                if (count == 1) {
                    qty.setText("1"); } else {
                    count -= 1;
                    qty.setText("" + count); }
            }
        });
    }
    public void Productdetails(String id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Productlist> call=apiInterface.getproduct(id);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Productdesciption.this);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        call.enqueue(new Callback<Productlist>() {
            @Override
            public void onResponse(Call<Productlist> call, Response<Productlist> response) {
                progressDoalog.dismiss();
                Productlist getallproduct = response.body();
                if(getallproduct==null){ Toast.makeText(Productdesciption.this, "No Product Found", Toast.LENGTH_SHORT).show();
                } else {
                    qtylayout.setVisibility(View.VISIBLE);
                    cart.setVisibility(View.VISIBLE);
                    category.setText(getallproduct.getCategory());
                    title.setText(getallproduct.getTitle());
                    price.setText("Rs. "+Float.toString(getallproduct.getPrice()));
                    description.setText(getallproduct.getDescription());
                    Glide.with(Productdesciption.this)
                            .load(getallproduct.getImage())
                            .into(image); }
            }
            @Override
            public void onFailure(Call<Productlist> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(Productdesciption.this,"Check Your Connectivity..",Toast.LENGTH_LONG).show(); }
        });
    }
}