package com.example.leoconnelly.connexus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by leoconnelly on 5/29/18.
 */


public class SelectCity extends AppCompatActivity {


    ImageButton bostonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);







        bostonButton = findViewById(R.id.bostonButton);

        //Sends user to health centers list page
        bostonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityToCenterList();
            }
        });




    }
    public void cityToCenterList() {

    Intent   goToHealthCenterList  = new Intent (this, HealthCenterListActivity.class);
    startActivity(goToHealthCenterList);

}


}