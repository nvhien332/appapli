package com.example.orderts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ConfirmActivity extends AppCompatActivity {
    private EditText hotenTxt, sdtTxt, diachiTxt, thanhphoTxt, txt_quantity;
    private Button xacnhanBtn;
    private String totalAmount = "";
    private String quantity ;
    private String tensp ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        totalAmount = getIntent().getStringExtra("Tổng = ");

        xacnhanBtn = findViewById(R.id.xacnhan);
        hotenTxt = findViewById(R.id.hoten);
        sdtTxt = findViewById(R.id.sdt);
        diachiTxt = findViewById(R.id.diachi);
        thanhphoTxt = findViewById(R.id.thanhpho);
        xacnhanBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Check();
            }
        });
    }


    private void Check()
    {
        String hoten = hotenTxt.getText().toString();
        String sdt = sdtTxt.getText().toString();
        String diachi = diachiTxt.getText().toString();
        String thanhpho = thanhphoTxt.getText().toString();

        if (TextUtils.isEmpty(hoten))
        {
            Toast.makeText(this, "Mời bạn nhập họ tên", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(sdt))
        {
            Toast.makeText(this, "Mời bạn nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(diachi))
        {
            Toast.makeText(this, "Mời bạn nhập địa chỉ", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(thanhpho))
        {
            Toast.makeText(this, "Mời bạn nhập Tỉnh/Thành phố", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmOrder();
        }
    }


    private void ConfirmOrder()
    {
        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("Order")
                .child(Common.currentOnlineUser.getPhone());

        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("totalAmount", totalAmount);
        ordersMap.put("name", hotenTxt.getText().toString());
        ordersMap.put("phone", sdtTxt.getText().toString());
        ordersMap.put("address", diachiTxt.getText().toString());
        ordersMap.put("city", thanhphoTxt.getText().toString());


        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child(Common.currentOnlineUser.getPhone()).removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ConfirmActivity.this,"Đặt hàng thành công", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ConfirmActivity.this, TrangchuActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
