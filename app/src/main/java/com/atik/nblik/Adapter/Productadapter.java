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
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Productadapter extends RecyclerView.Adapter<Productadapter.SpecificationViewHolder>{
    ArrayList<Productlist> arrayList;
    Context context;
    private static int currentPosition = -1;
    public Productadapter(ArrayList<Productlist> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context; }
    @Override
    public Productadapter.SpecificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlistadapter, parent, false);
        return  new SpecificationViewHolder(itemView);    }
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final Productadapter.SpecificationViewHolder holder, final int position) {
        holder.category.setText(arrayList.get(position).getCategory());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.price.setText("Rs. "+Float.toString(arrayList.get(position).getPrice()));
        Glide.with(context)
                .load(arrayList.get(position).getImage())
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class SpecificationViewHolder extends RecyclerView.ViewHolder {
        private TextView category,title,price;
        private ImageView image;
        @SuppressLint("NewApi")
        public SpecificationViewHolder(View itemView) {
            super(itemView);
            category=(TextView)itemView.findViewById(R.id.category);
            title=(TextView)itemView.findViewById(R.id.title);
            price=(TextView)itemView.findViewById(R.id.price);
            image=(ImageView) itemView.findViewById(R.id.image);
        }
    }
}
