package com.example.orderts;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText phone, pass;
    Button dnhap, dky;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        dky = findViewById(R.id.dky);

        firebaseAuth = FirebaseAuth.getInstance();

        dky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = phone.getText().toString();
                String mkhau = pass.getText().toString();
                if(TextUtils.isEmpty(sdt)){
                    phone.setError("Vui lòng nhập số điện thoại");
                    return;
                }
                if (TextUtils.isEmpty(mkhau)){
                    pass.setError("Vui lòng nhập mật khẩu");
                    return;
                }
                else CreatAccount(sdt, mkhau);
            }
        });

        dnhap = findViewById(R.id.dnhap);
        dnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CreatAccount(final String phone, final String pass) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("User").child(phone).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("pass", pass);
                    RootRef.child("User").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(SignupActivity.this, "Đã tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else {Toast.makeText(SignupActivity.this,"Lỗi: vui lòng thử lại sau", Toast.LENGTH_SHORT).show();}
                        }
                    });
                }
                else {
                    Toast.makeText(SignupActivity.this, "Số điện thoại " + phone + " đã được đăng ký", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
