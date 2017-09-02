package com.example.swapnilgupta.sharechat.homescreen;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.swapnilgupta.sharechat.R;
import com.example.swapnilgupta.sharechat.api.FeedsServiceApiImpl;
import com.example.swapnilgupta.sharechat.data.FeedItemsRepoImpl;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenContract.View {

    //TextView textView;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;

    private HomeScreenAdapter mAdapter;

    private static final String TAG = "HomeScreenActivity";
    private HomeScreenContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // initializing the adapter ...
        mAdapter = new HomeScreenAdapter(new ArrayList<FeedItem>(), mItemListener);

        // setting up the view ...
        recyclerView = (RecyclerView) findViewById(R.id.rvFeed);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srlFeed);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // setting the toolbar ..
        setSupportActionBar(toolbar);

        // setting swipe refresh layout ..
        refreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));

        // setting up the recyclerview ..
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        // setting up the swipe refresh layout ..
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionListener.refreshItems();
            }
        });

        // instantiating the presented for the view ..
        mUserActionListener = new HomeScreenPresenter(this,
                new FeedItemsRepoImpl(new FeedsServiceApiImpl()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mUserActionListener.subscribe();
        mUserActionListener.refreshItems();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mUserActionListener.unsubscribe();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        refreshLayout.setRefreshing(active);
    }

    @Override
    public void showItems(List<FeedItem> items) {
        mAdapter.setItems(items);
    }

    FeedsItemListener mItemListener = new FeedsItemListener() {
        @Override
        public void onFeedClicked(FeedItem item) {
            mUserActionListener.openClickFeedItem(item);
        }
    };

    public interface FeedsItemListener {
        void onFeedClicked(FeedItem item);
    }
}
