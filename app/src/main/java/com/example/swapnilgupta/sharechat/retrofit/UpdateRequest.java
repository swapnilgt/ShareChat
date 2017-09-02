package com.example.swapnilgupta.sharechat.retrofit;

import com.example.swapnilgupta.sharechat.models.FeedItem;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class UpdateRequest {

    private static final String request_id = "swapnilgupta.iiith@gmail.com";
    private FeedItem data;

    public UpdateRequest(FeedItem data) {
        this.data = data;
    }
}
