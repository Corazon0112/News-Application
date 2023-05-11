package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.newsapp.Model.Article;
import com.example.newsapp.Model.Headline;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 pager;
    FragmentAdapter adapter;
    Adapter adapter2;
    List<Article> articles = new ArrayList<>();
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.view_pager);

        adapter2 = new Adapter(this,articles);

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setBackgroundColor(Color.LTGRAY);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm,getLifecycle());
        pager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));
        tabLayout.addTab(tabLayout.newTab().setText("Science"));
        tabLayout.addTab(tabLayout.newTab().setText("Health"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pos = tab.getPosition();
                pager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

}