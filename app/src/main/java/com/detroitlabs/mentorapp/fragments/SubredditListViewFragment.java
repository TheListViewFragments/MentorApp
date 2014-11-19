package com.detroitlabs.mentorapp.fragments;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

public class SubredditListViewFragment extends ListFragment {

    public static final String LISTING_MODELS_KEY = "listingModels";
    ArrayList<ListingModel> mListingModels = new ArrayList<ListingModel>();


    public SubredditListViewFragment(){}

    public static SubredditListViewFragment newInstance(ArrayList<ListingModel> listingModels){
        Bundle args = new Bundle();
        args.putSerializable(LISTING_MODELS_KEY, listingModels);
        SubredditListViewFragment subredditListViewFragment =  new SubredditListViewFragment();
        subredditListViewFragment.setArguments(args);
        return subredditListViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();
        if (data != null){
            mListingModels = (ArrayList<ListingModel>) data.getSerializable(LISTING_MODELS_KEY);
        }

        setUpArrayAdapter(mListingModels);
    }

    public void setUpArrayAdapter(ArrayList<ListingModel> listingModels){
        ArrayAdapter<ListingModel> listingModelArrayAdapter = new ArrayAdapter<ListingModel>(getActivity(), android.R.layout.simple_list_item_2, listingModels){

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

                TextView author = (TextView) rowView.findViewById(android.R.id.text1);
                author.setText(getItem(position).getAuthor());

                TextView title = (TextView) rowView.findViewById(android.R.id.text2);
                title.setText(getItem(position).getTitle());

                rowView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //GO TO THIRD SCREEN FROM HERE AND DELETE THIS TOAST
                        Toast.makeText(getActivity(), "YOU CLICKED " + getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

                return rowView;
            }

        };

        setListAdapter(listingModelArrayAdapter);

    }

}
