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
    public ListingInterface listingInterface;

    /**
     * we are passing in interface so that we can call methods on it
     *
     * @param listingInterface
     */
    public SubredditApiRequest(ListingInterface listingInterface) {
        this.listingInterface = listingInterface;
    }

    @Override
    protected ArrayList<ListingModel> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String subredditName = params[0];
        String redditJsonString;

        try {
            String urlString = URL_BASE + subredditName;

            //turns our string into a url so that we can use it in a URL Connection
            URL subredditURL = new URL(urlString);
            //opens a URL connection to the defined URL
            urlConnection = (HttpURLConnection) subredditURL.openConnection();
            //tells the URL Connection to Get info rather than Post info
            urlConnection.setRequestMethod("GET");
            //ok, now connect
            urlConnection.connect();

            //gets the bytes
            InputStream inputStream = urlConnection.getInputStream();

            //used to organize the characters brought in from bufferedReader into a string
            StringBuffer buffer = new StringBuffer();

            //turn the bytes into characters via InputStreamReader and use BufferedReader to speed
            //up the process to take it in line-by-line
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            redditJsonString = buffer.toString();

        } catch (IOException e) {
            //goes here if we attempt to connect to an invalid URL

            return null;
        } finally {
            closeConnections(urlConnection, bufferedReader);
        }
        try {
            ArrayList<ListingModel> listingInformation = new SubredditJsonParser().parsePostingFromJsonString(redditJsonString);
            return listingInformation;

        } catch (JSONException e) {
            return null;
        }
    }

    private void closeConnections(HttpURLConnection urlConnection, BufferedReader bufferedReader) {
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


    @Override
    protected void onPostExecute(ArrayList<ListingModel> listingModels) {
        super.onPostExecute(listingModels);
        //here we call a method on our interface object (which is our Mainactivity) to pass an
        //ArrayList of listing models back to the main activity's getArrayListOfListings method
        listingInterface.getArrayListOfListings(listingModels);
    }
}
