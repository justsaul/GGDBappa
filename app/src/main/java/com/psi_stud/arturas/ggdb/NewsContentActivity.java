package com.psi_stud.arturas.ggdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NewsContentActivity extends AppCompatActivity {
    private News news;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Bundle b = getIntent().getExtras();

        news = new News(b.getInt("newsID"));

        news.loadNews();
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvContent = (TextView) findViewById(R.id.tvContent);

        tvTitle.setText(news.getTitle());
        tvAuthor.setText(news.getAuthor());
        tvContent.setText(news.getContent());
    }

}