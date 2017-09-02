package com.example.swapnilgupta.sharechat.data;

import com.example.swapnilgupta.sharechat.api.FeedsServiceApi;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;

import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class FeedItemsRepoImpl implements FeedItemsRepository {

    private FeedsServiceApi mFeedsServiceApi;

    private List<FeedItem> items;

    public FeedItemsRepoImpl(FeedsServiceApi mFeedsServiceApi) {
        this.mFeedsServiceApi = mFeedsServiceApi;
    }

    @Override
    public void loadMoreFeedsFromRemote(LoadFeedsCallback callback) {

    }

    @Override
    public void refreshFeedsFromRemote(final LoadFeedsCallback callback) {
        mFeedsServiceApi.loadFeed(0, new FeedsServiceApi.LoadFeedsServiceCallback() {
            @Override
            public void onLoaded(EnvelopeFetchFeeds eff) {
                if(eff != null) {
                    callback.onLoaded(eff.getData());
                } else {
                    callback.onLoaded(null);
                }
            }
        });
    }


    @Override
    public void loadFeedsFromLocal(LoadFeedsCallback callback) {
        // TODO - complete the code to implement it using SQL ..
    }

    @Override
    public void updateFeed(FeedItem item, UpdateFeedsCallback callback) {

    }
}
