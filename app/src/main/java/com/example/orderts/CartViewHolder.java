package com.example.orderts;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtName, txtPrice, txtQuantity;
    public ImageView imageView;
    private ItemClickListner itemClickListner;

    public CartViewHolder(View itemView){
        super(itemView);

        txtName = itemView.findViewById(R.id.txt_name);
        txtPrice = itemView.findViewById(R.id.txt_price);
        txtQuantity = itemView.findViewById(R.id.txt_quantity);
        imageView = itemView.findViewById(R.id.imageView);

    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}


