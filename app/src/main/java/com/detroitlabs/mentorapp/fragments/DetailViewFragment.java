package com.detroitlabs.mentorapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.model.ListingModel;

/**
 * Created by aditishetty on 11/18/14.
 */
public class DetailViewFragment extends Fragment {
    private final String LISTING_MODELS_KEY = "listingModels";
    private TextView titleView;
    private TextView authorView;
    private TextView descriptionView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_profile_view, container, false);
        titleView = (TextView) rootview.findViewById(R.id.title);
        authorView = (TextView) rootview.findViewById(R.id.author);
        descriptionView = (TextView) rootview.findViewById(R.id.text_or_image);

        final ListingModel listingBundle = getArguments().getParcelable(LISTING_MODELS_KEY);
        titleView.setText(listingBundle.getTitle());
        authorView.setText(listingBundle.getAuthor());
        descriptionView.setText(listingBundle.getSelfText());

        return rootview;
    }
}
