package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Parcelable;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.fragments.SubredditListViewFragment;
import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

/**
 * Created by Borham on 11/18/14.
 */
public class SubredditListViewActivity extends Activity {

    public static final String TAG = "SubredditListViewActivity";
    String subreddit;
    public ArrayList<ListingModel> subredditListings;
    static final String SUBREDDIT_CHOICE_KEY = "SUBREDDIT_CHOICE_KEY";
    public static final String SUBREDDIT_LISTINGS_KEY = "SUBREDDIT_LISTINGS_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        subredditListings = new ArrayList<ListingModel>();

        subreddit = getIntent().getStringExtra(SUBREDDIT_CHOICE_KEY);

        ArrayList<Parcelable> arrayListParcelables = getIntent().getParcelableArrayListExtra(SUBREDDIT_LISTINGS_KEY);

        for (Parcelable thisParcelable : arrayListParcelables){
            subredditListings.add((ListingModel)thisParcelable);
        }

        FragmentManager fm = getFragmentManager();
        Fragment subbredditListViewFragment = fm.findFragmentById(R.id.container);

        if (subbredditListViewFragment ==null) {

            subbredditListViewFragment = SubredditListViewFragment.newInstance(subredditListings);
            fm.beginTransaction()
                    .add(R.id.container, subbredditListViewFragment)
                    .commit();
        }

    }
}
