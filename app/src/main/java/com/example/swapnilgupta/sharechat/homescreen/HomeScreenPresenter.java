package com.example.swapnilgupta.sharechat.homescreen;

import android.content.Context;
import android.content.Intent;

import com.example.swapnilgupta.sharechat.data.FeedItemsRepository;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.profiledetails.ProfileDetailsActivity;

import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class HomeScreenPresenter implements HomeScreenContract.UserActionListener {

    private boolean isSubscribedToView;
    private HomeScreenContract.View mView;
    private FeedItemsRepository mRepository;
    private boolean isOccupied;

    public HomeScreenPresenter(HomeScreenContract.View mView, FeedItemsRepository mRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
        isSubscribedToView = false;
        isOccupied = false;
    }

    @Override
    public void subscribe() {
        isSubscribedToView = true;
    }

    @Override
    public void unsubscribe() {
        isSubscribedToView = false;
    }

    @Override
    public void loadItems() {
        if(isOccupied) {
            return;
        }
        isOccupied = true;

        if(isSubscribedToView) {
            mView.setProgressIndicator(true);
        }

        mRepository.loadFeedsFromLocal(new FeedItemsRepository.LoadFeedsCallback() {
            @Override
            public void onLoaded(List<FeedItem> itemList) {
                postLoadItems(itemList);
            }
        });
    }

    @Override
    public void loadMoreItems() {
        // Already serving a data request ..
        if(isOccupied) {
            return;
        }
        isOccupied = true;

        if(isSubscribedToView) {
            mView.setProgressIndicator(true);
        }
        mRepository.loadMoreFeedsFromRemote(new FeedItemsRepository.LoadFeedsCallback() {
            @Override
            public void onLoaded(List<FeedItem> itemList) {
                postLoadItems(itemList);
            }
        });
    }

    @Override
    public void refreshItems() {
        // Already serving a data request ..
        if(isOccupied) {
            return;
        }
        isOccupied = true;

        if(isSubscribedToView) {
            mView.setProgressIndicator(true);
        }
        mRepository.refreshFeedsFromRemote(new FeedItemsRepository.LoadFeedsCallback() {
            @Override
            public void onLoaded(List<FeedItem> itemList) {
                postLoadItems(itemList);
            }
        });
    }

    private void postLoadItems(List<FeedItem> itemList) {
        isOccupied = false;
        if(isSubscribedToView && itemList != null) {

            // adding load more to the list ..
            final FeedItem f = new FeedItem();
            f.setType(FeedItem.TYPE_LOAD_MORE);

            itemList.add(f);

            mView.showItems(itemList);

            mView.setProgressIndicator(false);
        }
    }

    @Override
    public void openClickFeedItem(FeedItem item, Context context) {
        switch (item.getType()) {
            case FeedItem.TYPE_LOAD_MORE:
                loadMoreItems();
                break;
            case FeedItem.TYPE_PROFILE:
                final Intent i = new Intent(context, ProfileDetailsActivity.class);
                i.putExtra(ProfileDetailsActivity.ARG_FEED_ID, item.getId());
                context.startActivity(i);
                break;
        }
    }
}
