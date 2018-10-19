package com.example.leoconnelly.connexus;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;
import com.helpshift.All;
import com.helpshift.Core;
import com.helpshift.InstallConfig;
import com.helpshift.exceptions.InstallException;
import com.helpshift.support.Log;
import com.helpshift.support.Support;

import java.io.File;
import java.util.Map;

import static com.google.android.gms.stats.GCoreWakefulBroadcastReceiver.startWakefulService;

public class MainApplication extends Application implements Support.Delegate {

    private String TAG = "Helpshift";

    @Override
    public void onCreate() {
        super.onCreate();

        Core.init(All.getInstance());
        InstallConfig installConfig = new InstallConfig.Builder()
                .setEnableInAppNotification(true)

                .setNotificationIcon(R.drawable.hs__cam_action_send_feedback)
                .build();

        try {
            Core.install(this,
                    "13cc365b1aca33c4f065e8462daf9cc4",
                    "connexus.helpshift.com",
                    "connexus_platform_20180418055627011-13c3cfe374d1ad2", installConfig);


        } catch (InstallException e) {
            android.util.Log.e("Helpshift", "install call : ", e);
        }


        android.util.Log.d("Helpshift", Support.libraryVersion + " - is the version for gradle");

        //Set Helpshift Delegate
        Support.setDelegate(this);

    }

    @Override
    public void sessionBegan() {
        Toast.makeText(getApplicationContext(), "Helpshift session began", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sessionEnded() {
        Toast.makeText(getApplicationContext(), "Helpshift session ended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void newConversationStarted(String newConversationMessage) {
        Log.d(TAG, "new conversation started with message : " + newConversationMessage);
    }

    @Override
    public void conversationEnded() {
        Log.d(TAG, "conversationEnded");
    }

    @Override
    public void userRepliedToConversation(String newMessage) {
        Log.d(TAG, "user replied with message : " + newMessage);
    }

    @Override
    public void userCompletedCustomerSatisfactionSurvey(int rating, String feedback) {
        Log.d(TAG, "user completed csat with rating " + rating + " and feedback : " + feedback);
    }

    @Override
    public void displayAttachmentFile(File attachmentFile) {
        Log.d(TAG, "no apps can open this file " + attachmentFile.getAbsolutePath());
    }

    @Override
    public void didReceiveNotification(int newMessagesCount) {
        Log.d(TAG, "new messages count : " + newMessagesCount);
    }
/*
    private void sendRegistrationIdToBackend() {
        // Send registrationId to Helpshift
        Core.registerDeviceToken(this, regid);
    }

    @Override
    public void onReceive(Context context,Intent intent) {
        if(intent.getExtras().getString("origin").equals("helpshift")) {
            Core.handlePush(context, intent);
        }
        // Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }


    @Override
    public void onMessageReceived(String from, Bundle data) {
        String origin = data.getString("origin");
        if (origin != null && origin.equals("helpshift")) {
            Core.handlePush(this, data);
        }
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        Map<String, String> data = message.getData();
        String origin = data.get("origin");
        if (origin != null && origin.equals("helpshift")) {
            Core.handlePush(this, data);
        }
    }


*/

}

