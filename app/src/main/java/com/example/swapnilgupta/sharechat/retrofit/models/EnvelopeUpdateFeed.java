package com.example.swapnilgupta.sharechat.retrofit.models;

import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

/**
 * Created by swapnilgupta on 04/09/2017.
 */

public class EnvelopeUpdateFeed {

    private boolean success = true;
    private FeedItem data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public FeedItem getData() {
        return data;
    }

    public void setData(FeedItem data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EnvelopeUpdateFeed{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
