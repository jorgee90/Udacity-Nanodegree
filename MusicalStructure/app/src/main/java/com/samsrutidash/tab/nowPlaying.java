package com.samsrutidash.tab;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by samsrutidash on 6/28/2016.
 */
public class nowPlaying extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from artists.xml
        View view = inflater.inflate(R.layout.now_playing, container, false);

        return view;
    }

}
