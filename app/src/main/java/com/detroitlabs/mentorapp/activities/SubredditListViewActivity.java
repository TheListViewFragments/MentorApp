package com.detroitlabs.mentorapp.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.fragments.SubredditListViewFragment;
import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

/**
 * Created by Borham on 11/18/14.
 */
public class SubredditListViewActivity extends Activity {
    public static final String TAG = "SubredditListViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentManager fm = getFragmentManager();
        Fragment subbredditListViewFragment = fm.findFragmentById(R.id.container);

        if (subbredditListViewFragment ==null) {

            //GET RID OF THIS STUFF ONCE WE HAVE A PROPER ARRAYLIST BUILT FROM JSON DATA
            ArrayList<ListingModel> thisListOfListings = new ArrayList<ListingModel>();
            ListingModel dummyModel = new ListingModel();
            ListingModel dummyModel2 = new ListingModel();
            dummyModel.setAuthor("BO");
            dummyModel.setTitle("Bo Jackson's List Item");
            thisListOfListings.add(dummyModel);
            dummyModel2.setAuthor("NOT BO");
            dummyModel2.setTitle("Not Bo Jackson's List Item");
            thisListOfListings.add(dummyModel2);
            //GET RID OF THIS STUFF ONCE WE HAVE A PROPER ARRAYLIST BUILT FROM JSON DATA

            subbredditListViewFragment = SubredditListViewFragment.newInstance(thisListOfListings);
            fm.beginTransaction()
                    .add(R.id.container, subbredditListViewFragment)
                    .commit();
        }

    }
}
