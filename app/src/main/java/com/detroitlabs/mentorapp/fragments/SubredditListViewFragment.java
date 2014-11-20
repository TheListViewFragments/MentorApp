package com.detroitlabs.mentorapp.fragments;


import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.detroitlabs.mentorapp.activities.SubredditListViewActivity;
import com.detroitlabs.mentorapp.interfaces.ListingInterface;
import com.detroitlabs.mentorapp.R;
import com.detroitlabs.mentorapp.model.ListingModel;
import com.detroitlabs.mentorapp.requests.SubredditApiRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SubredditListViewFragment extends ListFragment implements ListingInterface {
    private final long TIMER_DELAY = 0;
    private final long TIMER_PERIOD = 60000;
    private final long MARKER_REMOVAL_TIME = 5000;
    public static final String LISTING_MODELS_KEY = "listingModels";
    public SubredditApiRequest subredditApiRequest;
    static final String SUBREDDIT_CHOICE_KEY = "SUBREDDIT_CHOICE_KEY";
    String subreddit;
    ArrayList<ListingModel> mListingModels = new ArrayList<ListingModel>();
    Timer timer;


    public SubredditListViewFragment(){

    }

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

        subreddit = getActivity().getIntent().getStringExtra(SUBREDDIT_CHOICE_KEY);


        Bundle data = getArguments();
        if (data != null){
            mListingModels = (ArrayList<ListingModel>) data.getSerializable(LISTING_MODELS_KEY);
        }

        setUpArrayAdapter(mListingModels);
    }




    @Override
    public void onResume() {
        super.onResume();
        timer = new Timer ();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod ();
            }
        }, TIMER_DELAY, TIMER_PERIOD); // updates each 60 secs
    }




    private void TimerMethod() {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        getActivity().runOnUiThread(refreshListView);
    }



    private Runnable refreshListView = new Runnable () {
        //This method runs in the same thread as the UI.

        //Do something to the UI thread here
        //Go through the list of the markers and call that remove on the ones that are too old (5s)
        public void run() {
            subredditApiRequest = new SubredditApiRequest(SubredditListViewFragment.this);
            subredditApiRequest.execute(subreddit);
        }
    };




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

//                rowView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //GO TO THIRD SCREEN FROM HERE AND DELETE THIS TOAST
//                        Toast.makeText(getActivity(), "YOU CLICKED " + getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                return rowView;
            }

        };

        setListAdapter(listingModelArrayAdapter);

    }


    @Override
    public void onPause() {
        if(timer != null){
            timer.cancel();
        }
        super.onPause();
    }



    @Override
    public void getArrayListOfListings(ArrayList<ListingModel> listOfListings) {
        Log.d("MainActivity", "Inside of getArrayListOfListings");
        Toast.makeText(getActivity(), "REFRESHED", Toast.LENGTH_SHORT).show();
        setUpArrayAdapter(listOfListings);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        final DetailViewFragment singleItemViewFragment = new DetailViewFragment();
        ListingModel singleListingModel = (ListingModel) getListAdapter().getItem(position);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (fragmentTransaction.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(LISTING_MODELS_KEY, singleListingModel);
            singleItemViewFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container,singleItemViewFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }
}

