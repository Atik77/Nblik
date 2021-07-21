package com.atik.nblik.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cartlist implements Serializable {
    private String id;
    private String userId;
    private String date;
    private ArrayList<products> products = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<com.atik.nblik.Model.products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<com.atik.nblik.Model.products> products) {
        this.products = products;
    }
}
