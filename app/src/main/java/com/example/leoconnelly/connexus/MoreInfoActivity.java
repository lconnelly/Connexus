package com.example.leoconnelly.connexus;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by leoconnelly on 4/11/18.
 */

public class MoreInfoActivity extends AppCompatActivity {
    ImageButton moreInfoImage;
    TextView moreInfoText;
    TextView titleText;
    View mrLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);

       Context mContext = this;

        final ArrayList<HealthCenterButton> healthCentersList = HealthCenterButton.getHealthCentersFromFile("healthCenters.json", this);

        String urlString = "https://imgur.com/Rs4ELqT";

        //Set line color
        mrLine = findViewById(R.id.mr_line);
        mrLine.setBackgroundColor(Color.parseColor("#FFFFFF"));

        moreInfoImage = findViewById(R.id.moreInfoPicture);
        Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(moreInfoImage);


        // moreInfoImage.findViewById(R.id.moreInfoPicture);
        moreInfoText = findViewById(R.id.more_info_text);
        moreInfoText.setText("yeet");
        moreInfoText.setTextColor(Color.parseColor("#FFFFFF"));

       // moreInfoImage = findViewById(R.id.moreInfoPicture);

        titleText = findViewById(R.id.about_the_prov);
        titleText.setTextColor(Color.parseColor("#FFFFFF"));
        titleText.setTextSize(18);
        //titleText.setText("Yah yah yeet");


        //text.setTextColor(Color.parseColor("#FFFFFF"));

        //get array

        final ArrayList <String> miniList = new ArrayList <String>();


        for (HealthCenterButton healthCenterButton : healthCentersList) {
            miniList.add(healthCenterButton.mini);
           System.out.println("BEEEP BEEP BEEP BOOP BOP BEEPED BOOP BEEP");
            System.out.println(miniList);
       }



       //set up array for mini iamges




    //int get intent

        //int index = this.getIntent(index);



        /*
        ImageView iw= (ImageView)findViewById(R.id.imageView1);

         */


        //make it scrollable
        moreInfoText.setMovementMethod(new ScrollingMovementMethod());

       // int index = getIntent(index);

        int index = getIntent().getExtras().getInt("index");

        System.out.println(index + "DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING DING LOOK HERE HERE HERE HERE HERE HERE HERE HERE");

        //String intentStringNumber = getIntent().getStringExtra("index");

//        int index = Integer.parseInt(intentStringNumber);

      // int index = 0;

        System.out.println(index +" BOOOP BOP BOOP BOOP BOPP BOOP");

        if (index == 0) {
            //Bowdoin

            moreInfoText.setText("Founded in Boston's Dorchester neighborhood in 1972 by community residents, " +
                    "Bowdoin Street Health Center remains focused on providing outstanding medical care while" +
                    " maintaining a tradition of working with residents to identify and meet their " +
                    "unique health needs. The mission of the health center is to provide excellent, " +
                    "compassionate care to our patients and support the health of the entire community.");
            Picasso.with(mContext).load("https://imgur.com/Vny2PUh.jpg").into(moreInfoImage);
           // moreInfoImage =
            //        Picasso.with(mContext).load(healthCenterButton.imageUrl).into(thumbnailImageView);

            titleText.setText("Bowdoin Street Health Center");



        }

        else  if (index == 3) {
            //BIDMC Chelsea

            moreInfoText.setText("In the midst of a thriving Chelsea neighborhood, " +
                    "we bring world-class health care to patients north of Boston. " +
                    "Our patients have the best of all worlds: dedicated staff, the atmosphere of a private practice, " +
                    "and access to the expertise, resources, and systems of Beth Israel Deaconess Medical Center.");

            Picasso.with(mContext).load("https://imgur.com/poRBkB9.jpg”").into(moreInfoImage);
            titleText.setText("BIDMC Chelsea");


        }

        else  if (index == 2) {
            //BIDMC
            moreInfoText.setText("Beth Israel Deaconess Medical Center (BIDMC) in Boston, Massachusetts is a teaching hospital of Harvard Medical School. It was formed out of the 1996 merger of Beth Israel Hospital (founded in 1916) and New England Deaconess Hospital (founded in 1896). Among independent teaching hospitals, Beth Israel Deaconess Medical Center consistently ranks in the top three recipients of biomedical research funding from the National Institutes of Health. Research funding totals nearly $200 million annually. BIDMC researchers run more than 850 active sponsored projects and 200 clinical trials. " +
                    "The Harvard-Thorndike General Clinical Research Center, " +
                    "the oldest clinical research laboratory in the United States, " +
                    "has been located on this site since 1973");


            Picasso.with(mContext).load("https://imgur.com/Rs4ELqT.jpg").into(moreInfoImage);

            titleText.setText("BIDMC");
        }

        else  if (index == 1) {
            //Dimock Community Health Center Complex
            moreInfoText.setText("Recognized nationally as a model for the delivery of comprehensive health and human services in an urban community, The Dimock Center provides the residents of Boston with convenient access to high quality, low cost health care and human services that might not otherwise be available to the communities we serve. " +
                    " In 2013, Dimock handled over 76,000 visits annually with 17,000 unique patients and " +
                    "clients representing a wide range of socioeconomic and ethnic backgrounds.");

            Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(moreInfoImage);

            titleText.setText("Dimock Community Health Center");
        }

        else  if (index == 4) {
            //Fenway Health
            moreInfoText.setText("For more than forty years, " +
                    "Fenway Health has been working to make life healthier for the people in our neighborhood, " +
                    "the LGBT community, people living with HIV/AIDS and the broader population. Fenway was " +
                    "founded in 1971 as part of the free clinic movement by students who believed that" +
                    " ''health care should be a right, not a privilege. In its early days, " +
                    "Fenway was a drop-in clinic providing free blood pressure checks and STD screenings. Over the years, Fenway obtained permanent space and incorporated as a freestanding health center with a staff of one volunteer doctor, one nurse and one intake worker. Today, Fenway Health has a budget of more than $90 million, a staff of 500 and a patient population of more than 28,000. " +
                    "Fenway Health is a Federally Qualified Community Health Center.");

            Picasso.with(mContext).load("https://imgur.com/3z24l6x.jpg").into(moreInfoImage);
            titleText.setText("Fenway Health");


        }

        else  if (index == 5) {
            //South Cove Medical Center

            moreInfoText.setText("South Cove Community Health Center ('South Cove') is the premier Asian community health center of Massachusetts. Founded in 1972, South Cove provides medical and behavioral health services and youth and family programs for approximately 32,000 clients at four locations in the greater Boston area. Our mission is to improve the health and well being of all medically underserved in Massachusetts, with a special focus on Asian Americans.  " +
                    "This mission is accomplished by providing high quality, " +
                    "community-based health care and health promotion programs " +
                    "that are accessible and linguistically and culturally competent for these populations.");

            Picasso.with(mContext).load("https://imgur.com/MLXZnWJ.jpg").into(moreInfoImage);

            titleText.setText("South Cove Medical Center");

        }

        else  if (index == 6) {
            //South Cove Chinatown
            moreInfoText.setText("South Cove Community Health Center ('South Cove') is the" +
                    " premier Asian community health center of Massachusetts. Founded in 1972, South Cove provides medical and" +
                    " behavioral health services and youth and family programs for approximately 32,000 clients at four locations in the greater Boston area. " +
                    "Our mission is to improve the health and well being of all medically underserved in Massachusetts, with a special focus on Asian Americans. " +
                    " This mission is accomplished by providing high quality," +
                    " community-based health care and health promotion programs" +
                    " that are accessible and " +
                    "linguistically and culturally competent for these populations");

            Picasso.with(mContext).load("https://imgur.com/maKdk8Z.jpg").into(moreInfoImage);
            titleText.setText("South Cove China Town");



        }




    }
}
