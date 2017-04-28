package com.b2cloud.paulin.codingchallenge.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.b2cloud.paulin.codingchallenge.R;
import com.b2cloud.paulin.codingchallenge.apps.Queue;
import com.b2cloud.paulin.codingchallenge.helper.SwipeListAdapter;
import com.b2cloud.paulin.codingchallenge.model.FeedItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthony Paulin
 * @since 27-04-2017
 * Main Activity used to show a feed from ABC.net.au
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    //TAG for debug
    private static final String TAG = MainActivity.class.getSimpleName();
    //URL for JSON data
    private static final String FEED_URL = "https://api.rss2json.com/v1/api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml";
    //Layout to refrech the view
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //List view who contains the articles
    private ListView mListView;
    //Adapter for the view
    private SwipeListAdapter adapter;
    //List with articles (Feed)
    private List<FeedItems> itemsList;

    /**
     * @param savedInstanceState Bundle with saved session
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        itemsList = new ArrayList<>();
        adapter = new SwipeListAdapter(this, itemsList);
        mListView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run(){
                mSwipeRefreshLayout.setRefreshing(true);
                fetchFeed();
            }
        });
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        fetchFeed();
    }

    /**
     * When resume, get the feed again
     */
    @Override
    public void onResume(){
        super.onResume();
        fetchFeed();
    }
    /**
     * Fetching feed json by making http call
     */
    private void fetchFeed() {
        // showing refresh animation before making http call
        mSwipeRefreshLayout.setRefreshing(true);
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, FEED_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        itemsList.clear();
                        //If the response is not null
                        if (response.length() > 0) {
                            try {
                                if (response.getString("status").compareTo("ok") == 0) {
                                    Log.d(TAG,response.getJSONObject("feed").getString("title"));
                                    Log.d(TAG,response.getJSONObject("feed").getString("url"));
                                    Log.d(TAG,response.getJSONObject("feed").getString("link"));
                                    Log.d(TAG,response.getJSONObject("feed").getString("author"));
                                    Log.d(TAG,response.getJSONObject("feed").getString("description"));
                                    Log.d(TAG,response.getJSONObject("feed").getString("image"));
                                    //Set the action bar title with the feed title requested
                                    getSupportActionBar().setTitle(response.getJSONObject("feed").getString("title"));
                                    //Get the items array (Articles)
                                    JSONArray itemsArray = new JSONArray(response.getString("items"));
                                    //Inverte the array (to get the first article in the first items of the list)
                                    for(int i = itemsArray.length()-1;i>=0;i--)
                                    {
                                        String image = null;
                                        JSONObject movieObj = itemsArray.getJSONObject(i);
                                        if(i==0){
                                            //If large top article (first article)
                                            image = movieObj.getJSONObject("enclosure").getString("link");
                                        }
                                        else{
                                            //If others articles
                                            image = movieObj.getString("thumbnail");
                                        }
                                        Log.d(TAG,""+i+" Title "+movieObj.getString("title"));
                                        //Format the date
                                        String date = new SimpleDateFormat("MMM d, yyyy hh:mm a").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(movieObj.getString("pubDate")));
                                        //Create a new item and send it to the list
                                        FeedItems m = new FeedItems(i, movieObj.getString("title"),date,image,movieObj.getString("link"));
                                        itemsList.add(0, m);
                                    }
                                    //Notify the adapter
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            catch(JSONException e){
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        // Stopping swipe refresh
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        error.printStackTrace();
                        //Stopping Swipe Refresh on error
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        // Adding request to request queue
        Queue.getInstance().addToRequestQueue(req);
    }
}
