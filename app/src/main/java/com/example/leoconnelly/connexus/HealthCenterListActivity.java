package com.example.leoconnelly.connexus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by leoconnelly on 4/9/18.
 */

public class HealthCenterListActivity extends AppCompatActivity {

    private ListView mListView ;
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitals_list_view);

        final ArrayList<HealthCenterButton> healthCentersList = HealthCenterButton.getHealthCentersFromFile("healthCenters.json", this);

        if (healthCentersList.size()==2) {
            System.out.println("YES YES YES YES");
        }


        mContext = this;


    //    String x = healthCentersList.get(2).toString();
      //  System.out.println(x);

        HealthCenterButtonAdapter adapter = new HealthCenterButtonAdapter(this, healthCentersList);


        //set the views
        mListView = findViewById(R.id.hospitals_list_view);
        mListView.setAdapter(adapter);


        //set up the positions for the next screen
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id){


              HealthCenterButton SelectedHealthCareCenter = healthCentersList.get(position);

                Intent detailIntent = new Intent(mContext, HealthCenterSelectedActivity.class);

                detailIntent.putExtra("latitude", SelectedHealthCareCenter.lat);
                detailIntent.putExtra("longitude", SelectedHealthCareCenter.longi);
                detailIntent.putExtra("nameOfCenter", SelectedHealthCareCenter.nameOfCenter);
                detailIntent.putExtra("neighborhood", SelectedHealthCareCenter.neighborhood);
                detailIntent.putExtra("imageUrl", SelectedHealthCareCenter.imageUrl);

                startActivity(detailIntent);

            }

        });

       //get arraylists



            //get adapter






        }

    }
