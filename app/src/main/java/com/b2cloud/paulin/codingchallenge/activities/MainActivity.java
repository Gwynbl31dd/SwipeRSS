package com.b2cloud.paulin.codingchallenge.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.b2cloud.paulin.codingchallenge.R;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    //TAG for debug
    private final String TAG = MainActivity.class.getSimpleName();
    //URL for JSON data
    private final String FEED_URL =
            "https://api.rss2json.com/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml";

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onRefresh() {

    }
}
