package com.example.swapnilgupta.sharechat.profiledetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swapnilgupta.sharechat.R;
import com.example.swapnilgupta.sharechat.api.FeedsServiceApiImpl;
import com.example.swapnilgupta.sharechat.data.FeedItemsRepoImpl;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.FeedItemUpdateReq;

public class ProfileDetailsActivity extends AppCompatActivity implements ProfileDetailsContract.View {

    public static final String ARG_FEED_ID = "ProfileDetailsActivity.ARG";
    private int mFeedId;
    private ProfileDetailsContract.UserActionListener mUserActionListener;

    private EditText mContact;
    private EditText mDob;
    private EditText mStatus;
    private EditText mGender;

    private FeedItem mSessionFeedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        mContact = (EditText) findViewById(R.id.etContact);
        mDob = (EditText) findViewById(R.id.etDob);
        mStatus = (EditText) findViewById(R.id.etStatus);
        mGender = (EditText) findViewById(R.id.etGender);
        TextView mSave = (TextView) findViewById(R.id.tvSave);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FeedItemUpdateReq f = new FeedItemUpdateReq(mSessionFeedItem);
                f.setAuthor_dob(mDob.getText().toString());
                f.setAuthor_gender(mGender.getText().toString());
                f.setAuthor_status(mStatus.getText().toString());
                f.setAuthor_contact(mContact.getText().toString());

                mUserActionListener.updateUserDetails(f);
            }
        });

        mFeedId = getIntent().getIntExtra(ARG_FEED_ID, -1);

        mUserActionListener = new ProfileDetailsPresenter(
                FeedItemsRepoImpl.getInstance(new FeedsServiceApiImpl()), this);
        mUserActionListener.subscribe();
        mUserActionListener.loadUserDetails(mFeedId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserActionListener.unsubscribe();
    }

    @Override
    public void showUserDetails(FeedItem item) {
        mSessionFeedItem = item;

        mContact.setText(item.getAuthor_contact());
        mDob.setText(item.getAuthor_dob());
        mStatus.setText(item.getAuthor_status());
        mGender.setText(item.getAuthor_gender());
    }

    @Override
    public void showUserPrompt(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
