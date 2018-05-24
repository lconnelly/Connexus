package com.example.leoconnelly.connexus;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by celiachen on 2/5/18.
 */

public class HealthCenterButton {

    // instance variables or fields
    public String nameOfCenter;
    public String neighborhood;
    public String imageUrl;
    public Double lat;
    public Double longi;

    // constructor
    // default

    // method
    // static methods that read the json file in and load into Recipe

    // static method that loads our healthCenters.json using the helper method
    // this method will return an array list of recipes constructed from the JSON
    // file
    public static ArrayList<HealthCenterButton> getHealthCentersFromFile(String filename, Context context){
        ArrayList<HealthCenterButton> healthCenterList = new ArrayList<HealthCenterButton>();


        // try to read from JSON file
        // get information by using the tags
        // construct a Recipe Object for each recipe in JSON
        // add the object to arraylist
        // return arraylist
        try{
            String jsonString = loadJsonFromAsset("healthCenters.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray recipes = json.getJSONArray("recipes");

            // for loop to go through each recipe in your recipes array

            for (int i = 0; i < recipes.length(); i++){
                HealthCenterButton recipe = new HealthCenterButton();
                recipe.nameOfCenter = recipes.getJSONObject(i).getString("title");
                recipe.neighborhood = recipes.getJSONObject(i).getString("url");
                recipe.imageUrl = recipes.getJSONObject(i).getString("image");
                recipe.lat = recipes.getJSONObject(i).getDouble("lat");
                recipe.longi = recipes.getJSONObject(i).getDouble("longi");
                // add to arraylist
                healthCenterList.add(recipe);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return healthCenterList;
    }


    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
