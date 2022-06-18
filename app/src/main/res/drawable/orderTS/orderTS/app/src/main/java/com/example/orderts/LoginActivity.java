package com.example.orderts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button dky,dnhap;
    EditText phone,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        dnhap = findViewById(R.id.dnhap);
        dky = findViewById(R.id.dky);
        dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        dnhap.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (phone.getText().length() != 0 && pass.getText().length() != 0) {
                   if (phone.getText().toString().equals("hien") && pass.getText().toString().equals("123")) {
                     Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(LoginActivity.this, TrangchuActivity.class);
                     startActivity(intent);
                 } else {
                     Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                 }
             } else {
                 Toast.makeText(LoginActivity.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT).show();
             }
         }
     }
     );

    }
}
