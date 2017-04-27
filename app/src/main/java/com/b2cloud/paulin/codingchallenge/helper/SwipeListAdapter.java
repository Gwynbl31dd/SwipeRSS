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
import com.b2cloud.paulin.codingchallenge.utilities.CustomVolleyRequestQueue;

import java.util.List;

/**
 * Created by pauli on 27-04-17.
 * List adaptater
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

        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        Log.d("position :",""+itemsList.get(position).getId());
        if(itemsList.get(position).getId()==0) {
            convertView = inflater.inflate(R.layout.list_row_top, null);
        }
        else {
            convertView = inflater.inflate(R.layout.list_row, null);
        }


        NetworkImageView mImageView = (NetworkImageView) convertView.findViewById(R.id.imageView);
        ImageLoader mImageLoader = CustomVolleyRequestQueue.getInstance(this.activity.getApplicationContext()).getImageLoader();

        //Use ic_launcher as default image loading and IC_dialog_alert as alert image
        mImageLoader.get(itemsList.get(position).getImage(), ImageLoader.getImageListener(mImageView,
                R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        mImageView.setImageUrl(itemsList.get(position).getImage(), mImageLoader);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(itemsList.get(position).getTitle());

        TextView date = (TextView) convertView.findViewById(R.id.dateView);
        date.setText(itemsList.get(position).getDate());
        return convertView;
    }
}
