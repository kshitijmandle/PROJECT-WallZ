package com.learn.project_wallz.FRAGMENTS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.project_wallz.R;

public class AboutFragMents extends Fragment {



    public AboutFragMents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_frag_ments, container, false);
        return view;
    }
}