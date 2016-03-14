package com.example.dpene.database.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dpene.database.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoseLifeFragment extends Fragment {


    public LoseLifeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lose_life, container, false);
    }

}
