package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsArticle extends AppCompatActivity {

    TextView title,source,date,description;
    ImageView imageView;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);

        title = findViewById(R.id.titleView2);
        source = findViewById(R.id.sourceView2);
        date = findViewById(R.id.dateView2);
        description = findViewById(R.id.Description);
        imageView = findViewById(R.id.image2);
        webview = findViewById(R.id.webView);

        Intent intent = getIntent();
        String titles = intent.getStringExtra("title");
        String sources = intent.getStringExtra("source");
        String dates = intent.getStringExtra("date");
        String desc = intent.getStringExtra("desc");
        String imageURL = intent.getStringExtra("imageURL");
        String URLS = intent.getStringExtra("URL");

        title.setText(titles);
        source.setText(sources);
        date.setText(dates);
        description.setText(desc);

        Picasso.with(NewsArticle.this).load(imageURL).into(imageView);

        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(URLS);
    }
}