package com.example.iterable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.iterable.iterableapi.*;
import org.json.JSONObject;
import java.util.*;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Iterable SDK
        IterableConfig config = new IterableConfig.Builder().build();

        // Key would be stored elsewhere if in production
        IterableApi.initialize(getApplicationContext(), "< API KEY REMOVED - DO NOT PUSH TO GITHUB - Here you would grab an env variable >", config);
        IterableApi.getInstance().setEmail("smalbois+3@gmail.com");
    }

    // Part 1 Assignment
    public void updateProfile(android.view.View v) {

        JSONObject datafields = new JSONObject();
        try {
            datafields.put("firstName", "sebastien");
            datafields.put("isRegisteredUser", true);
            datafields.put("SA_User_Test_Key", "completed");
        } catch (Exception e) {
            e.printStackTrace();
        }

        IterableApi.getInstance().updateUser(datafields);
    }

    // Part 2 Assignment
    public void sendEvent(android.view.View v) {

        JSONObject datafields = new JSONObject();

        try {
            datafields.put("platform", "Android");
            datafields.put("isTestEvent", true);
            datafields.put("url", "https://iterable.com/sa-test/sebastien");
            datafields.put("secret_code_key", "Code_123");
        } catch (Exception e) {
            e.printStackTrace();
        }

        IterableApi.getInstance().track("mobileSATestEvent", datafields);

    }

    // If I want to overide the call the SDK is doing with processMessages
    public void chkMsgInQueue(android.view.View v) {

        // Get the in-app messages
        IterableInAppManager inAppManager = IterableApi.getInstance().getInAppManager();

        List<IterableInAppMessage> messages = inAppManager.getMessages();

        if (messages.size() > 0) {
            Log.v("Assignment", "message(s) found in the queue " + messages.size());
            // Show first message in that case!
            inAppManager.showMessage(messages.get(0));
        } else
            // No messages to show!
            Log.v("Assignment", "No messages to consume: " + messages.size());
    }

}
