package com.nickb.spots.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickb.spots.R;

//Cleaning things up and Tab Preparation (Part 5) - [Build an Instagram Clone]

public class ListViewFragment extends Fragment {
    private static final String TAG = "ListViewFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        return view;

    }
}