package com.detroitlabs.mentorapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detroitlabs.mentorapp.R;

/**
 * Created by aditishetty on 11/18/14.
 */
public class DetailViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_view, container, false);
        return view;
    }
}
