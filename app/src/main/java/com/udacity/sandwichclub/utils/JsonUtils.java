package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.content.res.Resources;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Settings.Global.getString;

public class JsonUtils {




    public static Sandwich parseSandwichJson(String json) {
        //strings from Sandwich.java
        String name = "";
        String mainName = "";
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = "";
        String description = "";
        String image = "";
        List<String> ingredients = new ArrayList<>();

        //create JSONObject from json string (imaginary http response)
        try {
            JSONObject jsonObj = new JSONObject(json);

            //get name object that include mainName & alsoKnownAs objects
            JSONObject nameObj = jsonObj.getJSONObject("name");

            //set name to the the mainName object
            name = nameObj.getString("mainName");

            //set mainName to value of mainName
            mainName = nameObj.getString("mainName");
            //debugging print statements
            System.err.println("name is:" + name);
            System.err.println("mainName is:" + mainName);


            //create JSONArray for alsoKnownAs
           JSONArray alsoKnownAsArray = nameObj.getJSONArray("alsoKnownAs");
            for (int i =0; i <alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }

            System.err.println("alsoKnownas is:" + alsoKnownAs);

            //get sandwich placeOfOrigin
            placeOfOrigin = jsonObj.getString("placeOfOrigin");
            System.err.println("placeOfOrigin is:" + placeOfOrigin);

            //get sandwich description
            description = jsonObj.getString("description");

            //get sandwich image
            image = jsonObj.getString("image");

            //need to work on ingredients
            JSONArray ingredientsArray = jsonObj.getJSONArray("ingredients");
            for (int i =0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }
            System.err.println("ingredients:" + ingredients);
            // return sandwich object
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }



}
