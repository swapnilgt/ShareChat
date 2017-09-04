package com.example.swapnilgupta.sharechat.retrofit;

import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.FeedItemUpdateReq;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class UpdateRequest {

    private String request_id = "swapnilgupta.iiith@gmail.com";
    private FeedItemUpdateReq data;

    public UpdateRequest(FeedItemUpdateReq data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "request_id='" + request_id + '\'' +
                ", data=" + data +
                '}';
    }
}
