package com.example.orderts.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderts.ProductDetail;
import com.example.orderts.R;
import com.example.orderts.Model.Store;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{

    ArrayList<Store> storeArrayList;

    public StoreAdapter(ArrayList<Store> productLocations) {
        this.storeArrayList = productLocations;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreAdapter.StoreViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, final int position) {
        holder.name.setText(storeArrayList.get(position).getName());
        holder.Price.setText(storeArrayList.get(position).getPrice() + " VND");
        Picasso.get().load(storeArrayList.get(position).getImage()).into(holder.image);

        holder.store_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetail.class);
                intent.putExtra("id", storeArrayList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder{
        CardView store_card;
        ImageView image;
        TextView name, Price;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            store_card=itemView.findViewById(R.id.card2);
            image = itemView.findViewById(R.id.anh1);
            name = itemView.findViewById(R.id.tv1);
            Price = itemView.findViewById(R.id.price);
        }
    }
}
