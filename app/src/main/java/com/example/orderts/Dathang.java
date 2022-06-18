package com.example.orderts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.orderts.Adapter.ViewPagerAdapter;
import com.example.orderts.Fragment.FragmentI10;
import com.example.orderts.Fragment.FragmentI11;
import com.example.orderts.Fragment.FragmentI6;
import com.example.orderts.Fragment.FragmentI7;
import com.example.orderts.Fragment.FragmentI8;
import com.google.android.material.tabs.TabLayout;

public class Dathang extends AppCompatActivity {
    TextView txt_quaylai;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        txt_quaylai = findViewById(R.id.txt_quaylai);

        txt_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dathang.this, TrangchuActivity.class);
                startActivity(intent);
            }
        });

        addTabs(viewPager);
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }
    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new FragmentI11(), "Iphone 11");
        adapter.add(new FragmentI10(), "Iphone X");
        adapter.add(new FragmentI8(), "Iphone 8");
        adapter.add(new FragmentI7(), "Iphone 7");
        adapter.add(new FragmentI6(), "Iphone 6");
        viewPager.setAdapter(adapter);
    }
}
