package com.example.leoconnelly.connexus;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by celiachen on 2/7/18.
 */

// adapter is needed when you want to do any sort of list or table view
// gets data and decides where to display in the activity

public class HealthCenterButtonAdapter extends BaseAdapter {

    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<HealthCenterButton> mHealthCentersList;
    private LayoutInflater mInflater;
    public Typeface custom_fontA;
    public Typeface custom_fontB;
    public Typeface custom_fontC;
    public Typeface custom_fontD;




    // constructor
    public HealthCenterButtonAdapter(Context mContext, ArrayList<HealthCenterButton> mHealthCentersList){

        //line of SO
        //super(mContext);

        mContext.getAssets();

        // initialize instances variables
        this.mContext = mContext;
        this.mHealthCentersList = mHealthCentersList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         custom_fontA = Typeface.createFromAsset(mContext.getAssets(),  "fonts/NeuzeitGro.ttf");
         custom_fontB = Typeface.createFromAsset(mContext.getAssets(),  "fonts/NeuzeitSLTBookHeavy.ttf");
         custom_fontC = Typeface.createFromAsset(mContext.getAssets(),  "fonts/DINNeuzeitGroteskStd-BdCond.otf");
         //custom_fontD = Typeface.createFromAsset(mContext.getAssets(),  "fonts/DINNeuzeitGroteskStd-Light.otf");




    }


    // methods
    // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount(){
        return mHealthCentersList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position){
        return mHealthCentersList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null){
            // inflate
            convertView = mInflater.inflate(R.layout.find_care_health_centers_list, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.nameOfCenterTextView = convertView.findViewById(R.id.health_center_list_title);
            holder.neighborhoodTextView = convertView.findViewById(R.id.neighborhood);
            holder.thumbnailImageView = convertView.findViewById(R.id.health_center_list_image);
            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)convertView.getTag();
        }

        //set custom fonts



        //end

        // get relavate subview of the row view
        TextView nameOfCenterTextView = holder.nameOfCenterTextView;
        TextView neighborhoodTextView = holder.neighborhoodTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        // get corresonpinding recipe for each row
        HealthCenterButton healthCenterButton = (HealthCenterButton)getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        nameOfCenterTextView.setText(healthCenterButton.nameOfCenter);
        nameOfCenterTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        nameOfCenterTextView.setTextSize(18);
        nameOfCenterTextView.setTypeface(custom_fontB);
        nameOfCenterTextView.setTextColor(Color.parseColor("#FFFFFF"));

        //text.setTextColor(Color.parseColor("#FFFFFF"));

        // servingTextView
        neighborhoodTextView.setText(healthCenterButton.neighborhood);
        neighborhoodTextView.setTextSize(14);
        neighborhoodTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        neighborhoodTextView.setTypeface(custom_fontA);
        neighborhoodTextView.setTextColor(Color.parseColor("#FFFFFF"));



        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(healthCenterButton.imageUrl).into(thumbnailImageView);




        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder{
        public TextView nameOfCenterTextView;
        public TextView neighborhoodTextView;
        public ImageView thumbnailImageView;
    }



    // intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver

}
