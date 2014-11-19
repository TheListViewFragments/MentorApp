package com.detroitlabs.mentorapp.parsers;

import com.detroitlabs.mentorapp.model.ListingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aditishetty on 11/19/14.
 */
public class SubredditJsonParser {

    private JSONObject jsonObject;

    private static final String DATA_KEY = "data";
    private static final String CHILDREN_KEY = "children";
    private static final String TITLE_KEY = "title";
    private static final String AUTHOR_KEY = "author";
    private static final String URL_KEY = "url";
    private static final String SELF_TEXT_KEY = "selftext";
    private static final String IS_SELF_KEY = "is_self";

    public static ArrayList<ListingModel> parsePostingFromJsonString(String redditJsonString) throws JSONException {

        JSONObject listingJsonObject = new JSONObject(redditJsonString);
        JSONObject dataJsonObject = listingJsonObject.getJSONObject(DATA_KEY);

    }
}
