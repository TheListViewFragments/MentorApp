package com.detroitlabs.mentorapp.requests;

import android.os.AsyncTask;

import com.detroitlabs.mentorapp.interfaces.ListingInterface;
import com.detroitlabs.mentorapp.model.ListingModel;
import com.detroitlabs.mentorapp.parsers.SubredditJsonParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by elyseturner on 11/19/14.
 */
public class SubredditApiRequest extends AsyncTask<String, Void, ArrayList<ListingModel>> {

    private final String URL_BASE = "http://api.reddit.com/r/";
    private String SUBREDDIT_NAME = "";
    public String redditJsonString;
    public ListingInterface listingInterface;

    public SubredditApiRequest(ListingInterface listingInterface) {
        this.listingInterface = listingInterface;
    }

    @Override
    protected ArrayList<ListingModel> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        SUBREDDIT_NAME = params[0];

        try{
            String urlString = URL_BASE + SUBREDDIT_NAME;

            URL subredditURL = new URL(urlString);

            urlConnection = (HttpURLConnection) subredditURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            redditJsonString = buffer.toString();

        }catch (IOException e) {

        return null;
    } finally {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (final IOException e) {
            }
        }
    }
        try {
            ArrayList<ListingModel> listingInformation = new SubredditJsonParser().parsePostingFromJsonString(redditJsonString);
            return listingInformation;

        } catch (JSONException e) {
            return null;
        }
    }


    @Override
    protected void onPostExecute(ArrayList<ListingModel> listingModels) {
        super.onPostExecute(listingModels);
        // implement interface to send arraylist back to main activity
        listingInterface.getArrayListOfListings(listingModels);

    }
}
