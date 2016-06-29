package com.samsrutidash.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

    public class songs extends Fragment {
        ListView list;
        String[] songTitle = {
                "Hello",
                "7 years",
                "Hello",
                "7 years",
                "Hello",
                "7 years"

        } ;
        String[] singerName = {
                "Adele",
                "Lucas Graham",
                "Adele",
                "Lucas Graham",
                "Adele",
                "Lucas Graham"
        };
        int[] imageId = {
                R.mipmap.image1,
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

            View view = inflater.inflate(R.layout.songs, container, false);

            list=(ListView) view.findViewById(R.id.list);
            list.setAdapter(new ListViewAdapter(view.getContext(),songTitle, imageId ));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(view.getContext(), "Now Playing Song: " +songTitle[+ position], Toast.LENGTH_SHORT).show();

                }
            });
            return view;
        }

    }

