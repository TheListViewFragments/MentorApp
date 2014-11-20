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

    public ArrayList<ListingModel> redditListingModelList = new ArrayList<ListingModel>();

    private static final String DATA_KEY = "data";
    private static final String CHILDREN_KEY = "children";
    private static final String TITLE_KEY = "title";
    private static final String AUTHOR_KEY = "author";
    private static final String URL_KEY = "url";
    private static final String SELF_TEXT_KEY = "selftext";
    private static final String IS_SELF_KEY = "is_self";

    public ArrayList<ListingModel> parsePostingFromJsonString(String redditJsonString) throws JSONException {

        JSONObject listingJsonObject = new JSONObject(redditJsonString);
        JSONObject dataJsonObject = listingJsonObject.getJSONObject(DATA_KEY);
        JSONArray childrenJsonArray = dataJsonObject.getJSONArray(CHILDREN_KEY);

        for(int i = 0; i < childrenJsonArray.length(); i++) {
            ListingModel redditListingModel = new ListingModel();
            JSONObject anotherDataJsonObject = childrenJsonArray.getJSONObject(i).getJSONObject(DATA_KEY);

            redditListingModel.setTitle(anotherDataJsonObject.getString(TITLE_KEY));
            redditListingModel.setAuthor(anotherDataJsonObject.getString(AUTHOR_KEY));
            redditListingModel.setListingUrl(anotherDataJsonObject.getString(URL_KEY));
            redditListingModel.setSelfText(anotherDataJsonObject.getString(SELF_TEXT_KEY));
            redditListingModel.setIsSelfText(anotherDataJsonObject.getBoolean(IS_SELF_KEY));
                // Android knows that one is a String and the other is a boolean

            redditListingModelList.add(redditListingModel);
        }

        return redditListingModelList;
    }
}
