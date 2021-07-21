package com.atik.nblik.Rest;

import com.atik.nblik.Model.Cartlist;
import com.atik.nblik.Model.Productlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {
    @GET("products")
    Call<ArrayList<Productlist>> products();
    @GET("products/{user_id}")
    Call<Productlist> getproduct(@Path(value = "user_id", encoded = true) String userId);
    @GET("carts")
    Call<ArrayList<Cartlist>> carts();
}
