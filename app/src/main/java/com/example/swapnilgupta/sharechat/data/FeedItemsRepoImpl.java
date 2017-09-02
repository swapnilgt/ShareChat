package com.example.swapnilgupta.sharechat.data;

import com.example.swapnilgupta.sharechat.api.FeedsServiceApi;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;

import java.util.ArrayList;
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
    public void loadMoreFeedsFromRemote(final LoadFeedsCallback callback) {
        int offset = 0;
        if(items != null) {
            offset = items.size();
        }
        mFeedsServiceApi.loadFeed(offset, new FeedsServiceApi.LoadFeedsServiceCallback() {
            @Override
            public void onLoaded(EnvelopeFetchFeeds eff) {
                if(eff != null) {
                    if(items != null) {
                        items.addAll(eff.getData());
                    } else {
                        items = eff.getData();
                    }
                }

                callback.onLoaded(new ArrayList<>(items));
            }
        });
    }

    @Override
    public void refreshFeedsFromRemote(final LoadFeedsCallback callback) {
        mFeedsServiceApi.loadFeed(0, new FeedsServiceApi.LoadFeedsServiceCallback() {
            @Override
            public void onLoaded(EnvelopeFetchFeeds eff) {
                if(eff != null) {
                    // Updating the list from the repo ..
                    items = eff.getData();
                }

                callback.onLoaded(new ArrayList<>(items));
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
