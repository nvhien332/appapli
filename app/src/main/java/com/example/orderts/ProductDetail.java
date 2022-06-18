package com.example.orderts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.orderts.Model.List;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.HashMap;

public class ProductDetail extends AppCompatActivity {
    Spinner spinner;
    String IdProduct;
    TextView tensp, giasp, tskithuat;
    ImageView anhsp, quaylai;
    Button addcart;
    ElegantNumberButton btn;
    String state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        IdProduct = getIntent().getStringExtra("id");

        tensp = findViewById(R.id.tensp);
        giasp = findViewById(R.id.giasp);
        tskithuat = findViewById(R.id.tskithuat);
        anhsp = findViewById(R.id.anhsp);
        addcart = findViewById(R.id.addcart);
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.btn);
        quaylai = findViewById(R.id.quaylai);
        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, Dathang.class);
                startActivity(intent);
            }
        });
        getDetail(IdProduct);


        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    addingToCartList();

            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    private void addingToCartList()
    {

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("id", IdProduct);
        cartMap.put("name", tensp.getText().toString());
        cartMap.put("Price", giasp.getText().toString() + " VND");
        cartMap.put("quantity", btn.getNumber());
//        cartMap.put("image", anhsp);
//        Log.d("IMAGE")

        cartListRef.child(Common.currentOnlineUser.getPhone())
                .child("Products").child(IdProduct)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ProductDetail.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }




    private void getDetail(String idProduct) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Fragment");
        productsRef.child(idProduct).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    List list = dataSnapshot.getValue(List.class);
                    tensp.setText(list.getName());
                    giasp.setText(list.getPrice() + " VND");
                    tskithuat.setText(list.getTskthuat());
                    Picasso.get().load(list.getImage()).into(anhsp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
