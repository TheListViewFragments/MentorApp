package com.detroitlabs.mentorapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

/**
 * Created by elyseturner on 11/18/14.
 */
public class SubredditListViewAdapter extends ArrayAdapter<ListingModel> {
    private final Context mContext;
    private ArrayList<ListingModel> mListingModel;

    public SubredditListViewAdapter(Context context, int resource, Context mContext, ArrayList<ListingModel> mListingModel) {
        super(context, resource);
        this.mContext = mContext;
        this.mListingModel = mListingModel;
    }
}
