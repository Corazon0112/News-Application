package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Article;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Article> articles;
    boolean isShimmer = true;
    int shimmerNumber = 5;

    public Adapter(Context context, List<Article> articles){
        this.context=context;
        this.articles=articles;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        if(isShimmer)
            holder.shimmerFrameLayout.startShimmer();
        else{
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            holder.title.setBackground(null);
            holder.source.setBackground(null);
            holder.date.setBackground(null);
            holder.imageView.setBackground(null);
            holder.background.setBackground(context.getResources().getDrawable(R.drawable.gradient));

            Article article = articles.get(position);
            holder.title.setText(article.getTitle());
            holder.source.setText(article.getSource().getName());
            holder.date.setText(time(article.getPublishedAt()));

            String imageURL = article.getUrlToImage();

            Picasso.with(context).load(imageURL).into(holder.imageView);

            if(holder.cardView != null) {
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, NewsArticle.class);
                        intent.putExtra("title", article.getTitle());
                        intent.putExtra("source", article.getSource().getName());
                        intent.putExtra("date", time(article.getPublishedAt()));
                        intent.putExtra("desc", article.getDescription());
                        intent.putExtra("imageURL", article.getUrlToImage());
                        intent.putExtra("URL", article.getUrl());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer?shimmerNumber:articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, source, date;
        ImageView imageView,background;
        CardView cardView;
        ShimmerFrameLayout shimmerFrameLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleView);
            source = itemView.findViewById(R.id.sourceView);
            date = itemView.findViewById(R.id.dateView);
            imageView = itemView.findViewById(R.id.image);
            background = itemView.findViewById(R.id.imageBackground);
            cardView = itemView.findViewById(R.id.cardView);
            shimmerFrameLayout = itemView.findViewById(R.id.frame);
        }
    }

    public String time(String t){
        String time = null;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd h:mm a");
            Date date = simpleDateFormat.parse(t);
            time = simpleDateFormat1.format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return time;
    }
}
