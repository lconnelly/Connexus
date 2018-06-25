package com.example.leoconnelly.connexus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.helpshift.All;
import com.helpshift.Core;
import com.helpshift.InstallConfig;
import com.helpshift.exceptions.InstallException;
import com.helpshift.support.ApiConfig;
import com.helpshift.support.Support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    ImageButton FindCareButton;
    ImageButton LearnButton;
    ImageButton TalkToADocButton;
    ImageButton VisitWebsite;
  //  Button CameraButton;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

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



           ByteArrayOutputStream out = new ByteArrayOutputStream();
           Bitmap find_care_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.findcare, options);
           find_care_bitmap.compress(Bitmap.CompressFormat.PNG, 60, out);
           Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
           FindCareButton = (findViewById(R.id.find_care_button));
           FindCareButton.setImageBitmap(decoded);




       // Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.find_care_layer);
      //  Bitmap bitmap=((BitmapDrawable)getResources().getDrawable(R.drawable.find_care_layer,null)).getBitmap();


        //BUTTONS AND WHAT THEY OPEN

        //THE PURPLE BUTTON!!!!!!!

        FindCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindCare();
            }
        });


        Bitmap learn_care_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.learn, options);
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        learn_care_bitmap.compress(Bitmap.CompressFormat.PNG, 60, out1);
        Bitmap decodedFindCare = BitmapFactory.decodeStream(new ByteArrayInputStream(out1.toByteArray()));
        LearnButton = (findViewById(R.id.learn));
        LearnButton.setImageBitmap(decodedFindCare);


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

        Bitmap talk_care_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.talk_doc, options);
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        talk_care_bitmap.compress(Bitmap.CompressFormat.PNG, 60, out2);
        Bitmap decodedTalkCare = BitmapFactory.decodeStream(new ByteArrayInputStream(out2.toByteArray()));
        TalkToADocButton = (ImageButton) findViewById(R.id.TalkToADocButton);
        TalkToADocButton.setImageBitmap(decodedTalkCare);

        TalkToADocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Support.showConversation(MainActivity.this);
            }
        });


        Bitmap visit_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.visit_web, options);
        ByteArrayOutputStream out3 = new ByteArrayOutputStream();
        visit_bitmap.compress(Bitmap.CompressFormat.PNG, 60, out3);
        Bitmap decodedVisit = BitmapFactory.decodeStream(new ByteArrayInputStream(out3.toByteArray()));
        VisitWebsite = (ImageButton) findViewById(R.id.VisitWeb);

        VisitWebsite.setImageBitmap(decodedVisit);



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

        //Set Fonts:




    }
//FIND CARE FUNCTION
    public void openFindCare() {
        Intent mainActivityToSelectCityFindCare = new Intent(this, SelectCity.class);
        startActivity(mainActivityToSelectCityFindCare);
    }

    /*
    public void openCamera() {
        Intent mainActivityToCamera = new Intent(this, RecordHealthInfo.class);
        startActivity(mainActivityToCamera);
    }

*/

    // Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
}
