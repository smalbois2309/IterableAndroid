package com.example.iterable;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.iterable.iterableapi.*;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "Assignment Iterable";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.v(TAG, "Push Message Received");
        IterableFirebaseMessagingService.handleMessageReceived(this, remoteMessage);
    }

    @Override
    public void onNewToken(String s) {

        Log.v(TAG, "Push Registration Token: " + s);
        IterableFirebaseMessagingService.handleTokenRefresh();
    }

}
