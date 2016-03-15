package com.example.dpene.database.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dpene.database.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoseLifeFragment extends DialogFragment {

    private Button continueButton;
    private Communicator parent;

    public LoseLifeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.parent = (Communicator) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NO_TITLE;
        int theme = android.R.style.Theme_Holo_Light_Dialog;

        setStyle(style, theme);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lose_life, container, false);

        continueButton = (Button) v.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Back to Activity
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        parent = null;
    }

}
