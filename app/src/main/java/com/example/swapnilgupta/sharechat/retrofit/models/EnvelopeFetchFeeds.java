package com.example.swapnilgupta.sharechat.retrofit.models;

import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class EnvelopeFetchFeeds {

    private boolean success = true;
    private List<FeedItem> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FeedItem> getData() {
        return data;
    }

    public void setData(List<FeedItem> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EnvelopeFetchFeeds{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
