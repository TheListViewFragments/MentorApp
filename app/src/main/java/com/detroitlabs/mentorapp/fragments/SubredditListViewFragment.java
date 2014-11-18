package com.detroitlabs.mentorapp.fragments;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elyseturner on 11/18/14.
 */

public class SubredditListViewFragment extends ListFragment {

    private static final String ETSY_OBJECT = "etsy object";
    private static final String QUEUE = "queue";
    private static final String SEARCH_KEYWORD_TAG = "search_keyword_tag";
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    List<ListingModel> resultList = new ArrayList<ListingModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new ListView(getActivity());
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        final SingleItemViewFragment singleItemViewFragment = new SingleItemViewFragment();
//        EtsyObjectsModel etsyObjectsModel = (EtsyObjectsModel) getListAdapter().getItem(position);
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//        if (fragmentTransaction.isEmpty()) {
//            Bundle bundle = new Bundle();
//            bundle.putParcelable(ETSY_OBJECT, etsyObjectsModel);
//            singleItemViewFragment.setArguments(bundle);
//            fragmentTransaction.replace(R.id.container, singleItemViewFragment);
//            fragmentTransaction.commit();
//
//        }
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final ResultsAdapter adapter = new ResultsAdapter(getActivity(), resultList);
        ListView myListView = getListView();

        setListAdapter(adapter);
        getArguments().getParcelableArrayList(QUEUE);
        EtsyAPI etsyAPI = new EtsyAPI(getArguments().getString(SEARCH_KEYWORD_TAG), new EtsyAPI.OnDataLoadedListener() {
            @Override
            public void dataLoaded(ArrayList<ListingModel> etsyObjectses) {
                adapter.clear();
                adapter.addAll(etsyObjectses);
                adapter.notifyDataSetChanged();
            }
        });

        etsyAPI.execute();
    }

    public class ResultsAdapter extends ArrayAdapter<ListingModel> {
        private List<ListingModel> searchResults;

        public ResultsAdapter(Context context, List<EtsyObjectsModel> etsyObjectModels) {
            super(context, android.R.layout.simple_list_item_1, etsyObjectModels);
            searchResults = etsyObjectModels;
        }

        @Override
        public int getCount() {
            return searchResults.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = LayoutInflater.from(getContext()).inflate(R.layout.search_results, parent, false);

            ImageView giftImage = (ImageView)rowView.findViewById(R.id.gift_image);
            TextView nameText = (TextView)rowView.findViewById(R.id.name_text);
            TextView priceText = (TextView)rowView.findViewById(R.id.price_text);

            EtsyObjectsModel currentGift = searchResults.get(position);
            nameText.setText(currentGift.getmTitle());
            priceText.setText("$" + currentGift.getmPrice());
            Picasso.with(getActivity()).load(currentGift.getmThumbnail()).into(giftImage);

            return rowView;
        }

    }

    //This is a way we can pass the search results from the API.
    public static SearchResultsFragment newInstance(ArrayList searchResults) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle results = new Bundle();
        results.putParcelableArrayList(QUEUE, searchResults);
        fragment.setArguments(results);
        return fragment;
    }
}
