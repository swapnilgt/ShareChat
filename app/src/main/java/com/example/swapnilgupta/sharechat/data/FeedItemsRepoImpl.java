package com.example.swapnilgupta.sharechat.data;

import android.util.Log;

import com.example.swapnilgupta.sharechat.api.FeedsServiceApi;
import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.profiledetails.ProfileDetailsContract;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;
import com.example.swapnilgupta.sharechat.sqlite.SQLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class FeedItemsRepoImpl implements FeedItemsRepository {

    private FeedsServiceApi mFeedsServiceApi;

    private List<FeedItem> items;

    public static FeedItemsRepository INSTANCE;

    public static FeedItemsRepository getInstance(FeedsServiceApi mFeedsServiceApi) {
        if(INSTANCE == null) {
            INSTANCE = new FeedItemsRepoImpl(mFeedsServiceApi);
        }
        return INSTANCE;
    }

    private FeedItemsRepoImpl(FeedsServiceApi mFeedsServiceApi) {
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
            public void onLoaded(final EnvelopeFetchFeeds eff) {
                if(eff != null) {
                    if(items != null) {
                        items.addAll(eff.getData());
                    } else {
                        items = eff.getData();
                    }
                }

                callback.onLoaded(new ArrayList<>(items));

                // inserting into database ..
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(eff != null) {
                            SQLUtils.insertNewFeeds(eff.getData());
                        }
                    }
                }).run();
            }
        });
    }

    @Override
    public void refreshFeedsFromRemote(final LoadFeedsCallback callback) {
        mFeedsServiceApi.loadFeed(0, new FeedsServiceApi.LoadFeedsServiceCallback() {
            @Override
            public void onLoaded(final EnvelopeFetchFeeds eff) {
                if(eff != null) {
                    // Updating the list from the repo ..
                    items = eff.getData();
                }

                callback.onLoaded(new ArrayList<>(items));

                // clearing database and then inserting the new data ...
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(eff != null) {
                            if (SQLUtils.flushLocalFeeds()) {
                                SQLUtils.insertNewFeeds(eff.getData());
                            }
                        }
                    }
                }).run();
            }
        });
    }


    @Override
    public void loadFeedsFromLocal(final LoadFeedsCallback callback) {
        // TODO - complete the code to implement it using SQL ..
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<FeedItem> it = SQLUtils.fetchFeedItems();
                callback.onLoaded(new ArrayList<>(it));
                items = it;

            }
        }).run();
    }

    @Override
    public void loadFeed(int id, LoadFeedCallback callback) {
        for(FeedItem i: items) {
            if(i.getId() == id) {
                callback.onLoaded(i);
                return;
            }
        }
    }

    @Override
    public void updateFeed(FeedItem item, final UpdateFeedsCallback callback) {
        mFeedsServiceApi.updateFeedItem(item, new FeedsServiceApi.UpdateFeedServiceCallback() {
            @Override
            public void onUpdate(boolean success, FeedItem item) {
                callback.onUpdateFeed(success, item);
            }
        });
    }
}
