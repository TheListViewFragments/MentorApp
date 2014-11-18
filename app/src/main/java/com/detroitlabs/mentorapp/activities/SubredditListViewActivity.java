package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.fragments.SubredditListViewFragment;

/**
 * Created by Borham on 11/18/14.
 */
public class SubredditListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        SubredditListViewFragment subredditListViewFragment = new SubredditListViewFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, subredditListViewFragment);
        fragmentTransaction.commit();
    }
}
