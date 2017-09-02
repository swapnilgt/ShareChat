package com.example.swapnilgupta.sharechat.retrofit;

import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public interface FeedsServiceRESTClient {

    @POST("/data")
    Call<EnvelopeFetchFeeds> fetchFeedsList(@Body FetchRequest request);

    @PUT("/update")
    Call<FeedItem> updateFeedItem(@Body UpdateRequest request);
}
