package com.example.swapnilgupta.sharechat.homescreen;

import com.example.swapnilgupta.sharechat.data.FeedItemsRepository;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class HomeScreenPresenter implements HomeScreenContract.UserActionListener {

    private boolean isSubscribedToView;
    private HomeScreenContract.View mView;
    private FeedItemsRepository mRepository;

    public HomeScreenPresenter(HomeScreenContract.View mView, FeedItemsRepository mRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
        isSubscribedToView = false;
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

    }

    @Override
    public void loadMoreItems() {

    }

    @Override
    public void refreshItems() {
        mRepository.refreshFeedsFromRemote(new FeedItemsRepository.LoadFeedsCallback() {
            @Override
            public void onLoaded(List<FeedItem> itemList) {
                if(isSubscribedToView && itemList != null) {
                    mView.showItems(itemList);
                }
                // TODO - Update this list in the Local storage in SQL ..
            }
        });
    }

    @Override
    public void openUserProfile(String userId) {

    }
}
