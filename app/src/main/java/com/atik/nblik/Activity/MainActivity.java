package com.atik.nblik.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.atik.nblik.Adapter.Productadapter;
import com.atik.nblik.R;
import com.atik.nblik.Rest.ApiClient;
import com.atik.nblik.Rest.ApiInterface;
import com.atik.nblik.Model.Productlist;
import com.atik.nblik.Util.RecyclerItemClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Productadapter adapter;
    RecyclerView productlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productlist=(RecyclerView)findViewById(R.id.productlist);
        Allproducts();
    }
    public void Allproducts(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Productlist>> call=apiInterface.products();
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        call.enqueue(new Callback<ArrayList<Productlist>>() {
            @Override
            public void onResponse(Call<ArrayList<Productlist>> call, Response<ArrayList<Productlist>> response) {
                progressDoalog.dismiss();
                ArrayList<Productlist> getallproduct = response.body();
                if(getallproduct==null){ Toast.makeText(MainActivity.this, "No Product Found", Toast.LENGTH_SHORT).show();
                } else {
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    adapter = new Productadapter(getallproduct, MainActivity.this);
                    productlist.setLayoutManager(layoutManager);
                    productlist.setAdapter(adapter);
                    productlist.addOnItemTouchListener(
                            new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    Intent intent=new Intent(MainActivity.this,Productdesciption.class);
                                    intent.putExtra("id",getallproduct.get(position).getId());
                                    startActivity(intent);
                                }})); } }
            @Override
            public void onFailure(Call<ArrayList<Productlist>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,"Check Your Connectivity..",Toast.LENGTH_LONG).show(); }
        });
    }
}