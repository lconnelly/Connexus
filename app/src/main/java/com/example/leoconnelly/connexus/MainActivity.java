package com.example.leoconnelly.connexus;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.helpshift.Core;
import com.helpshift.All;
import com.helpshift.InstallConfig;
import com.helpshift.exceptions.InstallException;

import com.helpshift.support.ApiConfig;
import com.helpshift.support.Support;

public class MainActivity extends AppCompatActivity {

    Button FindCareButton;
    Button LearnButton;
    Button TalkToADocButton;
    Button VisitWebsite;
  //  Button CameraButton;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//fonts and shit

        //TextView textView = (TextView) findViewById(R.id.learn_text_view);
        //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DINNeuzeitGroteskStd-BdCond.otf");
        //textView.setTypeface(typeface);


        //HELPSHIFT SET UP
        Core.init(All.getInstance());
        InstallConfig installConfig = new InstallConfig.Builder()
                .setEnableInAppNotification(true)
                .build();

        try {
            Core.install(getApplication(),
                    "13cc365b1aca33c4f065e8462daf9cc4",
                    "connexus.helpshift.com",
                    "connexus_platform_20180418055627011-13c3cfe374d1ad2", installConfig);


        } catch (InstallException e) {
            android.util.Log.e("Helpshift", "install call : ", e);
        }

        setContentView(R.layout.activity_main);


        //BUTTONS AND WHAT THEY OPEN

        //THE PURPLE BUTTON!!!!!!!
        FindCareButton = (findViewById(R.id.find_care_button));

        FindCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindCare();
            }
        });



        //LEARN BUTTON
        LearnButton = (Button) findViewById(R.id.learn);


        LearnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ApiConfig.Builder configBuilder = new ApiConfig.Builder();
                configBuilder.setRequireEmail(true);
                configBuilder.setEnableTypingIndicator(true);
                Support.showFAQs(MainActivity.this, configBuilder.build());

                //com.helpshift.support.Support.showConversation(MainActivity.this, configBuilder.build() );


            }
        });

        //TALK TO A DOC
        TalkToADocButton = (Button) findViewById(R.id.TalkToADocButton);

        TalkToADocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Support.showConversation(MainActivity.this);
            }
        });



        VisitWebsite = (Button) findViewById(R.id.VisitWeb);

        VisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Uri uri = Uri.parse("https://www.Connexushealth.org");
                Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(VisitWebsite);
            }
        });



        /*CameraButton = (Button) findViewById(R.id.camera_button);
        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });

            */


    }
//FIND CARE FUNCTION
    public void openFindCare() {
        Intent mainActivityToFindCare = new Intent(this, HealthCenterListActivity.class);
        startActivity(mainActivityToFindCare);
    }

    /*
    public void openCamera() {
        Intent mainActivityToCamera = new Intent(this, RecordHealthInfo.class);
        startActivity(mainActivityToCamera);
    }

*/

}
