package com.example.swapnilgupta.sharechat.api;

import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;
import com.example.swapnilgupta.sharechat.retrofit.models.FeedItemUpdateReq;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public interface FeedsServiceApi {

    interface LoadFeedsServiceCallback {
        void onLoaded(EnvelopeFetchFeeds eff);
    }

    void loadFeed(int offsetId, LoadFeedsServiceCallback callback);

    interface UpdateFeedServiceCallback {
        void onUpdate(boolean success, FeedItem item);
    }

    void updateFeedItem(FeedItemUpdateReq item, UpdateFeedServiceCallback callback);


}
