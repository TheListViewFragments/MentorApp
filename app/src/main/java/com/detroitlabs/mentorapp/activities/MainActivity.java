package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.interfaces.ListingInterface;
import com.detroitlabs.mentorapp.model.ListingModel;
import com.detroitlabs.mentorapp.requests.SubredditApiRequest;

import java.util.ArrayList;


public class MainActivity extends Activity implements ListingInterface {
    EditText editText;
    Button button;
    String subreddit = "";
    static final String SUBREDDIT_CHOICE_KEY = "SUBREDDIT_CHOICE_KEY";
    public static final String SUBREDDIT_LISTINGS_KEY = "SUBREDDIT_LISTINGS_KEY";
    public SubredditApiRequest subredditApiRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.search_string_text);
        button = (Button) findViewById(R.id.first_page_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() != 0) {
                    subreddit = (editText.getText().toString().trim());

                    subredditApiRequest = new SubredditApiRequest(MainActivity.this);
                    subredditApiRequest.execute(subreddit);

                }

            }
        });
    }

    @Override
    public void getArrayListOfListings(ArrayList<ListingModel> listOfListings) {
        Log.d("MainActivity", "Inside of getArrayListOfListings");
        Intent launchListViewIntent = new Intent(getApplicationContext(), SubredditListViewActivity.class);
        launchListViewIntent.putExtra(SUBREDDIT_CHOICE_KEY, subreddit);
        launchListViewIntent.putParcelableArrayListExtra(SUBREDDIT_LISTINGS_KEY,listOfListings);
        startActivity(launchListViewIntent);
    }
}
