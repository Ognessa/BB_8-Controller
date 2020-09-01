package com.example.bb_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter; //class PageAdapter
    TabItem tabControl;
    TabItem tabMusic;
    TabItem tabBluetooth;
    TabItem tabSetup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = findViewById(R.id.tabLayout);
        tabControl = findViewById(R.id.tabConctol);
        tabMusic = findViewById(R.id.tabMusic);
        tabBluetooth = findViewById(R.id.tabBluetooth);
        tabSetup = findViewById(R.id.tabSetup);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter  = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount()); //find class

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
