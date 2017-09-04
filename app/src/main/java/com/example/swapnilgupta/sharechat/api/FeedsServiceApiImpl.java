package com.example.swapnilgupta.sharechat.api;

import android.util.Log;

import com.example.swapnilgupta.sharechat.models.FeedItem;
import com.example.swapnilgupta.sharechat.retrofit.FeedsServiceRESTClient;
import com.example.swapnilgupta.sharechat.retrofit.FetchRequest;
import com.example.swapnilgupta.sharechat.retrofit.UpdateRequest;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeFetchFeeds;
import com.example.swapnilgupta.sharechat.retrofit.models.EnvelopeUpdateFeed;
import com.example.swapnilgupta.sharechat.retrofit.models.FeedItemUpdateReq;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by swapnilgupta on 02/09/2017.
 */

public class FeedsServiceApiImpl implements FeedsServiceApi {

    private FeedsServiceRESTClient mClient;

    private static final String TAG = "FeedsServiceApiImpl";

    public FeedsServiceApiImpl() {
        // Initializing the retrofit instance ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://35.154.249.234:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mClient = retrofit.create(FeedsServiceRESTClient.class);
    }

    @Override
    public void loadFeed(int offsetId, final LoadFeedsServiceCallback callback) {

        Log.d(TAG, "Request with offset: " + offsetId);

        Call<EnvelopeFetchFeeds> call = mClient.fetchFeedsList(new FetchRequest(offsetId));
        call.enqueue(new Callback<EnvelopeFetchFeeds>() {
            @Override
            public void onResponse(Call<EnvelopeFetchFeeds> call, Response<EnvelopeFetchFeeds>
                    response) {
                Log.d(TAG, "Success in fetching data: " + response.body());
                if(response.body().isSuccess()) {
                    callback.onLoaded(response.body());
                } else {
                    callback.onLoaded(null);
                }
            }

            @Override
            public void onFailure(Call<EnvelopeFetchFeeds> call, Throwable t) {
                Log.d(TAG, "Error in fetching the list" + t);
                callback.onLoaded(null);
            }
        });
    }

    @Override
    public void updateFeedItem(FeedItemUpdateReq item, final UpdateFeedServiceCallback callback) {

        Log.d(TAG, "Running update on: " + new UpdateRequest(item));

        Call<EnvelopeUpdateFeed> call = mClient.updateFeedItem(new UpdateRequest(item));
        call.enqueue(new Callback<EnvelopeUpdateFeed>() {
            @Override
            public void onResponse(Call<EnvelopeUpdateFeed> call, Response<EnvelopeUpdateFeed> response) {
                if(response.body().isSuccess()) {
                    callback.onUpdate(true, response.body().getData());
                } else {
                    callback.onUpdate(false, null);
                }
            }

            @Override
            public void onFailure(Call<EnvelopeUpdateFeed> call, Throwable t) {
                callback.onUpdate(false, null);
            }
        });
    }
}
