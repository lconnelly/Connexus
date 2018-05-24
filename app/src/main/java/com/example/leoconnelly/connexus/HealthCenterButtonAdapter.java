package com.example.leoconnelly.connexus;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    // constructor
    public HealthCenterButtonAdapter(Context mContext, ArrayList<HealthCenterButton> mHealthCentersList){

        // initialize instances variables
        this.mContext = mContext;
        this.mHealthCentersList = mHealthCentersList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

        // servingTextView
        neighborhoodTextView.setText(healthCenterButton.neighborhood);
        neighborhoodTextView.setTextSize(14);
        neighborhoodTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

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
