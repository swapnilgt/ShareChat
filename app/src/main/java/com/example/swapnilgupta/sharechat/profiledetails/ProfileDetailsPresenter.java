package com.example.swapnilgupta.sharechat.profiledetails;

import com.example.swapnilgupta.sharechat.data.FeedItemsRepository;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.FeedItemUpdateReq;

/**
 * Created by swapnilgupta on 03/09/2017.
 */

public class ProfileDetailsPresenter implements ProfileDetailsContract.UserActionListener {


    private FeedItemsRepository mFeedsItemsRepository;
    private ProfileDetailsContract.View mView;
    private boolean isViewSubscribed;

    public ProfileDetailsPresenter(FeedItemsRepository mFeedsItemsRepository,
                                   ProfileDetailsContract.View mView) {
        this.mFeedsItemsRepository = mFeedsItemsRepository;
        this.mView = mView;
        isViewSubscribed = false;
    }


    @Override
    public void updateUserDetails(FeedItemUpdateReq item) {
        mFeedsItemsRepository.updateFeed(item, new FeedItemsRepository.UpdateFeedsCallback() {
            @Override
            public void onUpdateFeed(boolean success, FeedItem item) {
                if(success) {
                    mView.showUserDetails(item);
                    mView.showUserPrompt("Successfully updated the details");
                } else {
                    mView.showUserPrompt("Failed to update the details");
                }
            }
        });
    }

    @Override
    public void loadUserDetails(int id) {
        mFeedsItemsRepository.loadFeed(id, new FeedItemsRepository.LoadFeedCallback() {
            @Override
            public void onLoaded(FeedItem item) {
                if(isViewSubscribed && item != null) {
                    mView.showUserDetails(item);
                }
            }
        });
    }

    @Override
    public void subscribe() {
        isViewSubscribed = true;
    }

    @Override
    public void unsubscribe() {
        isViewSubscribed = false;
    }
}
