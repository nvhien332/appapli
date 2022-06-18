package com.example.orderts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.orderts.Model.Cart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Giohang extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button nextBtn;
    TextView totalPrice, txtTong;
    ImageView imageView;

    private int tongTien = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        nextBtn = findViewById(R.id.next_btn);
        recyclerView= findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        imageView = findViewById(R.id.imageView);
        totalPrice= findViewById(R.id.total_price);
        txtTong = findViewById(R.id.txtTong);


        txtTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                totalPrice.setText(String.valueOf(Common.totalPrice));
                totalPrice.setText(String.valueOf(tongTien));
//                intent.putExtra("Tổng = ", String.valueOf(tongTien));
            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Giohang.this, ConfirmActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().
                setQuery(cartListRef.child(Common.currentOnlineUser.getPhone()).child("Products"), Cart.class).build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull final Cart cart)
            {
                cartViewHolder.txtName.setText(cart.getName());
                cartViewHolder.txtPrice.setText("Giá tiền: " + cart.getPrice());
                cartViewHolder.txtQuantity.setText("Số Lượng: " + cart.getQuantity());

                Log.d("GIOHANG",cartViewHolder.imageView.toString());
                Picasso.get().load(cart.getImage()).into(cartViewHolder.imageView);

//                 int tong = ((Integer.valueOf(cart.getPrice())) * Integer.valueOf(cart.getQuantity()));
                int tong = (Integer.valueOf(cart.getQuantity()));
                 tongTien = tongTien + tong;


                cartViewHolder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        CharSequence options[]=new CharSequence[]
                                {
                                        "Sửa",
                                        "Xóa"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                        builder.setTitle("Tùy chọn");

                        builder.setItems(options, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                if (which == 0)
                                {
                                    Intent intent = new Intent(Giohang.this, ProductDetail.class);
                                    intent.putExtra("id", cart.getId());
                                    startActivity(intent);
                                }
                                if (which == 1)
                                {
                                    cartListRef.child(Common.currentOnlineUser.getPhone()).child("Products").child(cart.getId())
                                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(Giohang.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }


            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
