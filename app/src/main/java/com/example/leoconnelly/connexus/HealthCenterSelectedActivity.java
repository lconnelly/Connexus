package com.example.leoconnelly.connexus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by leoconnelly on 4/10/18.
 */

public class HealthCenterSelectedActivity extends AppCompatActivity {
    MapView mMapView;
    private GoogleMap googleMap;
    //private WebView mWebView;
    public static final String KEY_URL = "key_url";
    public static final String KEY_TITLE = "key_title";
    Context mContext;
    public double lat;
    public double longi;
    Typeface boldFont;
    Typeface buttonFont;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_care_selected);

        //if else statement

        buttonFont = Typeface.createFromAsset(this.getAssets(),  "fonts/Roboto-Regular.ttf");
        boldFont = Typeface.createFromAsset(this.getAssets(),  "fonts/Roboto-Bold.ttf");



        //String title = this.getIntent().getExtras().getString("title");

    String nameOfCenterFromIntent = this.getIntent().getExtras().getString("nameOfCenter");
    lat = this.getIntent().getExtras().getDouble("latitude");
    longi = this.getIntent().getExtras().getDouble("longitude");

        System.out.println(nameOfCenterFromIntent + "beep beep beep beep BEEP BEEP BEEP BEEP BEEP BEEP BEEP BEEP BEEP BEEPBEEP BEEP BEEP BEEPBEEP BEEP BEEP BEEPBEEP BEEP BEEP BEEPBEEP BEEP BEEP BEEP");

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        //if
        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                //set location
                LatLng Bowdoin = new LatLng(lat, longi);
                googleMap.addMarker(new MarkerOptions().position(Bowdoin).title("Marker Title").snippet("Marker Description"));

                // zoom automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(Bowdoin).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        TextView centerName = findViewById(R.id.Address);
        Button GetDirections = findViewById(R.id.GetDirections);
        Button call = findViewById(R.id.Call); //done
        Button visitWebsite = findViewById(R.id.VisitWebsite); //done
        Button moreInfo = findViewById(R.id.MoreInfo);


        //DEAL WITH UI
        GetDirections.setTypeface(buttonFont);
        GetDirections.setTextColor(Color.BLACK);


        call.setTypeface(buttonFont);
        call.setTextColor(Color.BLACK);

        visitWebsite.setTypeface(buttonFont);
        visitWebsite.setTextColor(Color.BLACK);

        moreInfo.setTypeface(buttonFont);
        moreInfo.setTextColor(Color.BLACK);



        centerName.setTypeface(boldFont);
        centerName.setTextSize(22);
        centerName.setTextColor(00000);


        //DEAL WITH ALL CAPS

        GetDirections.setTransformationMethod(null);
        call.setTransformationMethod(null);
        visitWebsite.setTransformationMethod(null);
        moreInfo.setTransformationMethod(null);


        //for more info, assign an int for each one.
        //Create the int in the onclick
        //if statments checking the value of the int ad

        mContext = this;




        GetDirections.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

/*
                Uri gmmIntentUri = Uri.parse("Google.navigation:q="+lat+","+longi);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
*/
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+lat+","+longi));
                startActivity(intent);

                // mWebView = findViewById(R.id.detail_web_view);
                //mWebView.loadUrl("www.youtube.com");

            }
        });

       // address.setText("230 Bowdoin St.");



        if (nameOfCenterFromIntent.equalsIgnoreCase("Bowdoin Health Center")) {
                System.out.println("TEST TEST TEST TEST TEST ");

            centerName.setText("230 Bowdoin St, Dorchester, MA 02122");




            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-754-0100"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

                //call


            //visit website
            //detail_web_view

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.bidmc.org/centersanddepartments/departments/communityhealthcenters/");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });


            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 0;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });


        }
        else if (nameOfCenterFromIntent.equalsIgnoreCase("Dimock Center")) {

            centerName.setText("55 Dimock St, Boston, MA 02119");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-442-8800"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.dimock.org/");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 1;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", 1);
                    startActivity(openMoreInfo0);
                }
            });

        }
        else if (nameOfCenterFromIntent.equalsIgnoreCase("BETH ISRAEL DEACONESS MEDICAL CENTER")) {

            centerName.setText("330 Brookline Ave, Boston, MA 02215");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-667-7000"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.bidmc.org");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 2;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });

        }
        else if (nameOfCenterFromIntent.equalsIgnoreCase("BETH ISRAEL CHELSEA")) {

            centerName.setText("1000 Broadway, Chelsea, MA 02150");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-975-6200"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.bidmc.org/Other-Locations/Beth-Israel-Deaconess-HealthCare-Chelsea.aspx");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 3;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });

        }
        else if (nameOfCenterFromIntent.equalsIgnoreCase("Fenway Health")) {

            centerName.setText("1340 Boylston St, Boston, MA 02215");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-267-0900"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://fenwayhealth.org/");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 4;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });

        }
        else if (nameOfCenterFromIntent.equalsIgnoreCase("South Cove Medical Center")) {
            centerName.setText("435 Hancock St, Quincy, MA 02171");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-318-3200"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.scchc.org/");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 5;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                   // openMoreInfo0.putExtra("miniUrl", SelectedHealthCareCenter.imageUrl);

                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });

        }

        else if (nameOfCenterFromIntent.equalsIgnoreCase("South Cove Community Health Center-Chinatown")) {

            centerName.setText("885 Washington St., Boston, MA 02111");


            call.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent callNum = new Intent(Intent.ACTION_DIAL);
                    callNum.setData(Uri.parse("tel:617-521-6750"));
                    startActivity(callNum);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            visitWebsite.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://www.scchc.org/");
                    Intent VisitWebsite = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(VisitWebsite);
                    // mWebView = findViewById(R.id.detail_web_view);
                    //mWebView.loadUrl("www.youtube.com");

                }
            });

            moreInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    int index = 6;

                    Intent openMoreInfo0 = new Intent (mContext, MoreInfoActivity.class);
                    openMoreInfo0.putExtra("index", index);
                    startActivity(openMoreInfo0);
                }
            });

        }




        //if name equals Beth Israel
        //Set up page for beth Israel

        //else if name equals somethign else
        //set up page for something else

        //maybe use switch

    }

}