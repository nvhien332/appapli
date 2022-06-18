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

import com.example.orderts.Model.List;
import com.example.orderts.ProductDetail;
import com.example.orderts.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    ArrayList<List> ListArrayList;

    public ListAdapter(ArrayList<List> productLocations) {
        this.ListArrayList = productLocations;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListAdapter.ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.name.setText(ListArrayList.get(position).getName());
        holder.Price.setText(ListArrayList.get(position).getPrice() + " VND");
        Picasso.get().load(ListArrayList.get(position).getImage()).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetail.class);
                intent.putExtra("id", ListArrayList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListArrayList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, Price;
        CardView cardView;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.anh1);
            name = itemView.findViewById(R.id.tv1);
            Price = itemView.findViewById(R.id.price);
        }
    }
}
