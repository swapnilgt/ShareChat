package com.example.swapnilgupta.sharechat.homescreen;

import com.example.swapnilgupta.sharechat.basecontract.BaseContract;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

/**
 * Created by swapnilgupta on 01/09/2017.
 */

interface HomeScreenContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showItems(List<FeedItem> items);

    }

    interface UserActionListener extends BaseContract.UserActionListener {

        void loadItems();

        void loadMoreItems();

        void refreshItems();

        void openFeedItem(FeedItem item);
    }
}
