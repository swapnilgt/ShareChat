package com.example.swapnilgupta.sharechat.basecontract;


/**
 * Created by swapnilgupta on 02/09/2017.
 */

public interface BaseContract {

    interface UserActionListener {
        void subscribe();
        void unsubscribe();
    }
}
