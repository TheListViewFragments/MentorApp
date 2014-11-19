package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.fragments.SubredditListViewFragment;

/**
 * Created by Borham on 11/18/14.
 */
public class SubredditListViewActivity extends Activity {
    String subreddit;
    static final String SUBREDDIT_CHOICE_KEY = "SUBREDDIT_CHOICE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        subreddit = getIntent().getStringExtra(SUBREDDIT_CHOICE_KEY);
        Log.d("LOG_TAG", "subreddit is " + subreddit);

        SubredditListViewFragment subredditListViewFragment = new SubredditListViewFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, subredditListViewFragment);
        fragmentTransaction.commit();
    }
}
