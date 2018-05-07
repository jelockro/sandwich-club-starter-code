package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        System.err.println(json);
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        // TextView name = findViewById();
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        TextView origin = findViewById(R.id.origin_tv);
        TextView description = findViewById(R.id.description_tv);
        TextView ingredients = findViewById(R.id.ingredients_tv);

        //fill in text for alsoKnownAs

        //list of alsoKnowns'
        List<String> alsoKnown = sandwich.getAlsoKnownAs();
        //Return String
        String knownResults = "";
        System.err.println(alsoKnown);

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            System.err.println("yes, alsoKnownAs is empty");
            alsoKnownAs.setText(R.string.no_data_for_sandwich);
        } else
            System.err.println("There are aliases");
            for (String alias : alsoKnown) {
               knownResults += alias + "\n";
                //debug print
               System.err.println(knownResults);
            }
            alsoKnownAs.setText(knownResults);


        //fill in text for origin

        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            origin.setText(R.string.no_data_for_sandwich);

        } else origin.setText(sandwich.getPlaceOfOrigin());


        //fill in text for description
        if (sandwich.getDescription().isEmpty()) {
            description.setText(R.string.no_data_for_sandwich);

        } else description.setText(sandwich.getDescription() + "\n");



        //fill in text for ingredients
        List<String> Ingredients = sandwich.getIngredients();
        //debug print
        //System.err.println("Ingedients from DetailActivity.java:" + Ingredients);
        String INGResults = "";

        if (sandwich.getIngredients().isEmpty()) {
            origin.setText(R.string.no_data_for_sandwich);
        } else
            for (String ingredient : Ingredients) {
                INGResults += ingredient + "\n";
                //debug print
                //System.err.println(INGResults);
            }

            ingredients.setText(INGResults);

    }
}
