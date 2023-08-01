package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        arrayList.add(R.drawable.icon_11);
        arrayList.add(R.drawable.icon_21);
        arrayList.add(R.drawable.icon_31);
        arrayList.add(R.drawable.icon_41);
        arrayList.add(R.drawable.icon_51);
        arrayList.add(R.drawable.icon_61);
        arrayList.add(R.drawable.icon_71);
        arrayList.add(R.drawable.icon_81);
        arrayList.add(R.drawable.icon_91);

        MyAdapter myAdapter =
                new MyAdapter(MainActivity.this, arrayList);
        viewPager.setAdapter(myAdapter);
    }
}