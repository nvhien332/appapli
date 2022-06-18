package com.example.orderts;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderts.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button dky,dnhap;
    EditText inputphone,inputpass;
    String DBName = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputphone = findViewById(R.id.phone);
        inputpass = findViewById(R.id.pass);
        dnhap = findViewById(R.id.dnhap);
        dky = findViewById(R.id.dky);

        dnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TrangchuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
             String phone = inputphone.getText().toString();
             String pass = inputpass.getText().toString();

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else loginAccount(phone,pass);
    }

    private void loginAccount(final String phone,final String pass) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(DBName).child(phone).exists()){
                    User user = dataSnapshot.child(DBName).child(phone).getValue(User.class);
                    if (user.getPhone().equals(phone)){
                        if (user.getPass().equals(pass)){
                            Common.currentOnlineUser = user;

                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, TrangchuActivity.class);
                            startActivity(intent);
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Số điện thoại chưa được đăng ký", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
