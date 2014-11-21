package com.detroitlabs.mentorapp.interfaces;

import com.detroitlabs.mentorapp.model.ListingModel;

import java.util.ArrayList;

/**
 * Created by Borham on 11/19/14.
 */
public interface ListingInterface {

    //creating a method that all objects implementing ListingInterface MUST use
    //it has no body because the object that implements our interface will decide for itself what the method does
    public void getArrayListOfListings (ArrayList<ListingModel> listOfListings);

}
