package com.atik.nblik.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atik.nblik.Adapter.Cartadapter;
import com.atik.nblik.R;
import com.atik.nblik.Rest.ApiClient;
import com.atik.nblik.Rest.ApiInterface;
import com.atik.nblik.Model.Cartlist;
import com.atik.nblik.Model.Productlist;
import com.atik.nblik.Util.RecyclerItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {
    Cartadapter adapter;
    RecyclerView productlist;
    ArrayList<Productlist> getallproduct;
    Button checkout;
    TextView total;
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        productlist=(RecyclerView)findViewById(R.id.productlist);
        checkout=(Button)findViewById(R.id.checkout);
        total=(TextView) findViewById(R.id.total);
        checkout.setVisibility(View.GONE);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Cart.this,Thankyou.class);
                startActivity(intent); }
        });
        Allproducts();
    }
    public void Cartlist(ArrayList<Productlist> productlistArrayList){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Cartlist>> call=apiInterface.carts();
        call.enqueue(new Callback<ArrayList<Cartlist>>() {
            @Override
            public void onResponse(Call<ArrayList<Cartlist>> call, Response<ArrayList<Cartlist>> response) {
                ArrayList<Cartlist> getallcart = response.body();
                if(getallcart==null){ Toast.makeText(Cart.this, "No Product in cart", Toast.LENGTH_SHORT).show();
                } else {
                    progressDoalog.dismiss();
                    checkout.setVisibility(View.VISIBLE);
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(Cart.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    adapter = new Cartadapter(productlistArrayList,getallcart.get(0).getProducts(), Cart.this);
                    productlist.setLayoutManager(layoutManager);
                    productlist.setAdapter(adapter);
                }}

            @Override
            public void onFailure(Call<ArrayList<Cartlist>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(Cart.this,"Check Your Connectivity..",Toast.LENGTH_LONG).show(); }
        });
    }
    public void Allproducts(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Productlist>> call=apiInterface.products();
        progressDoalog = new ProgressDialog(Cart.this);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        call.enqueue(new Callback<ArrayList<Productlist>>() {
            @Override
            public void onResponse(Call<ArrayList<Productlist>> call, Response<ArrayList<Productlist>> response) {
                getallproduct = response.body();
                if(getallproduct==null){ Toast.makeText(Cart.this, "Check your internet connectivity", Toast.LENGTH_SHORT).show();
                } else {
                    Cartlist(getallproduct);
                }}
            @Override
            public void onFailure(Call<ArrayList<Productlist>> call, Throwable t) {
            } });
    }}