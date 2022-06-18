package com.example.orderts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.orderts.Adapter.ListAdapter;
import com.example.orderts.Adapter.StoreAdapter;
import com.example.orderts.Model.List;
import com.example.orderts.Model.Store;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class TrangchuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Object ImageView;
    ViewFlipper viewFlipper;
    Animation in, out;
    RecyclerView recyclerStore;
    ArrayList<Store> store;
    StoreAdapter storeAdapter;
    RecyclerView recyclerList;
    ArrayList<List> list;
    ListAdapter listAdapter;
    DatabaseReference reference;
    //drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    android.widget.ImageView menuIcon;
    public TrangchuActivity() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        recyclerStore = findViewById(R.id.recycler_store);
        recyclerStore();

        recyclerList = findViewById(R.id.recycler_list);
        recyclerList();

        menuIcon = findViewById(R.id.menu_icon);
        //menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.bringToFront();
        navigationDrawer();
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void navigationDrawer() {
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void recyclerStore() {
        recyclerStore.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        store = new ArrayList<Store>();

        reference = FirebaseDatabase.getInstance().getReference().child("Store");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Store s = dataSnapshot1.getValue(Store.class);
                    store.add(s);
                }
                storeAdapter = new StoreAdapter(store);
                recyclerStore.setAdapter(storeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TrangchuActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void recyclerList(){
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<List>();

        reference = FirebaseDatabase.getInstance().getReference().child("List");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    List s = dataSnapshot1.getValue(List.class);
                    list.add(s);
                }
                listAdapter = new ListAdapter(list);
                recyclerList.setAdapter(listAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TrangchuActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent3 = new Intent(TrangchuActivity.this, TrangchuActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_dangxuat:
                Intent intent = new Intent(TrangchuActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);
                break;
            case R.id.nav_giohong:
                Intent intent1 = new Intent(TrangchuActivity.this, Giohang.class);
                startActivity(intent1);
                break;
            case R.id.nav_dathang:
                Intent intent2 = new Intent(TrangchuActivity.this, Dathang.class);
                startActivity(intent2);
                break;
        }
        return false;
    }
}
