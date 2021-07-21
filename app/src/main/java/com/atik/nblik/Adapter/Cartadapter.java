package com.atik.nblik.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.atik.nblik.R;
import com.atik.nblik.Model.Productlist;
import com.atik.nblik.Model.products;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Cartadapter extends RecyclerView.Adapter<Cartadapter.SpecificationViewHolder>{
    ArrayList<products> arrayList;
    ArrayList<Productlist> getallproduct;
    Context context;
    private static int currentPosition = -1;
    public Cartadapter(ArrayList<Productlist> getallproduct, ArrayList<products> arrayList, Context context) {
        this.getallproduct = getallproduct;
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public Cartadapter.SpecificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlistadapter, parent, false);
        return  new SpecificationViewHolder(itemView);    }
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final Cartadapter.SpecificationViewHolder holder, final int position) {
        holder.qty.setText("Quantity- "+arrayList.get(position).getQuantity());
        if (getallproduct.get(position).getId().equals(arrayList.get(position).getProductId()))
        {
            holder.category.setText(getallproduct.get(position).getCategory());
            holder.title.setText(getallproduct.get(position).getTitle());
            String PRiceofsingle=Float.toString(getallproduct.get(position).getPrice()*Float.parseFloat(arrayList.get(position).getQuantity()));
            holder.price.setText("Rs. "+ PRiceofsingle);
            Glide.with(context)
                .load(getallproduct.get(position).getImage())
                .into(holder.image);
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class SpecificationViewHolder extends RecyclerView.ViewHolder {
        private TextView category,title,qty,price;
        private ImageView image;
        @SuppressLint("NewApi")
        public SpecificationViewHolder(View itemView) {
            super(itemView);
            category=(TextView)itemView.findViewById(R.id.category);
            title=(TextView)itemView.findViewById(R.id.title);
            qty=(TextView)itemView.findViewById(R.id.qty);
            price=(TextView)itemView.findViewById(R.id.price);
            image=(ImageView) itemView.findViewById(R.id.image);
        }
    }
}
