package com.detroitlabs.mentorapp.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

public class SubredditListViewFragment extends ListFragment {

    ArrayList<ListingModel> mListingModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayAdapter<ListingModel> listingModelArrayAdapter = new ArrayAdapter<ListingModel>(getActivity(), android.R.layout.simple_list_item_2, mListingModels){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView author = (TextView) view.findViewById(android.R.id.text1);
                author.setText(mListingModels.get(position).getAuthor());

                TextView title = (TextView) view.findViewById(android.R.id.text2);
                title.setText(mListingModels.get(position).getAuthor());

                return view;
            }
        };

        setListAdapter(listingModelArrayAdapter);

    }

}
