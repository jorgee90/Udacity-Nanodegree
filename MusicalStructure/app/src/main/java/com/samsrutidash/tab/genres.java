package com.samsrutidash.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by samsrutidash on 6/28/2016.
 */
public class genres extends Fragment {
    GridView grid;
    String[] web = {
            "Hip Hop",
            "Rock",
            "Romantic",
            "Pop",
            "Pop/Single",

    } ;
    int[] imageId = {
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
            R.mipmap.image1,
        };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get the view from artists.xml

        View view = inflater.inflate(R.layout.artists, container, false);
        grid=(GridView) view.findViewById(R.id.grid);
        grid.setAdapter(new GridViewAdapter(view.getContext(),web, imageId ));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(view.getContext(), "You will be redirected to the Songs of Genre: " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

}
