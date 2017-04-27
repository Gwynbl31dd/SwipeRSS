package com.b2cloud.paulin.codingchallenge.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.b2cloud.paulin.codingchallenge.R;
import com.b2cloud.paulin.codingchallenge.model.FeedItems;

import java.util.List;

/**
 * Created by pauli on 27-04-17.
 */

public class SwipeListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItems> itemsList;

    public SwipeListAdapter(Activity activity, List<FeedItems> itemsList) {
        this.activity = activity;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int location) {
        return itemsList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
