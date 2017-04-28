package com.b2cloud.paulin.codingchallenge.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.b2cloud.paulin.codingchallenge.R;
import com.b2cloud.paulin.codingchallenge.model.FeedItems;
import com.b2cloud.paulin.codingchallenge.utilities.CustomVolleyRequestQueue;

import java.util.List;

/**
 * @author Anthony Paulin
 * @since 27-04-2017
 * List adaptater
 */
public class SwipeListAdapter extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItems> itemsList;

    /**
     * @param activity the activity who calls
     * @param itemsList A list of Items for the feed
     */
    public SwipeListAdapter(Activity activity, List<FeedItems> itemsList) {
        this.activity = activity;
        this.itemsList = itemsList;
    }

    /**
     *
     * @return the list count
     */
    @Override
    public int getCount() {
        return itemsList.size();
    }

    /**
     *
     * @param location Location of the items
     * @return return the item as an object
     */
    @Override
    public Object getItem(int location) {
        return itemsList.get(location);
    }

    /**
     *
     * @param position Position of the item in the list
     * @return the id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @param position position of the item in the list
     * @param convertView the view
     * @param parent the parent view
     * @return convertView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

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
