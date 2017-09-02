package com.example.swapnilgupta.sharechat.homescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.swapnilgupta.sharechat.R;
import com.example.swapnilgupta.sharechat.api.FeedsServiceApiImpl;
import com.example.swapnilgupta.sharechat.data.FeedItemsRepoImpl;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenContract.View {

    TextView textView;

    private static final String TAG = "HomeScreenActivity";
    private HomeScreenContract.UserActionListener mUserActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        textView = (TextView) findViewById(R.id.textView);

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

    }

    @Override
    public void showItems(List<FeedItem> items) {
        textView.setText(items.toString());
    }
}
