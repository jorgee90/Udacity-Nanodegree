package com.samsrutidash.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Restoran extends Fragment {
    ListView list;
    LocationDetails location_restoran_data[] = new LocationDetails[]
            {

                    new LocationDetails("The Pavilion", "Address: Lingkaran Syed Putra, Mid Valley City, 58000 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur, Malaysia\n"
                            , R.drawable.insidepavilion,14.5234, 12.3123),
                    new LocationDetails("ABC", "Descin", R.mipmap.ic_launcher,
                            14.5234, 12.3123),
                    new LocationDetails("ABC", "Descin", R.mipmap.ic_launcher,
                            14.5234, 12.3123)
            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.restoran, container, false);

        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(new ListViewAdapter(view.getContext(), location_restoran_data));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LocationDetails current = location_restoran_data[position];
//                    Toast.makeText(view.getContext(), "Wait..." , Toast.LENGTH_SHORT).show();
                Intent details = new Intent(getActivity(),LocationFullDisplay.class);
                details.putExtra("locationName" , current.getLocationName());
                details.putExtra("locationDesc",current.getLocationDesc());
                details.putExtra("locationLON", current.getLon());
                details.putExtra("locationLAT", current.getLat());
                details.putExtra("locationIcon", current.getLocationIcon());
                startActivity(details);
            }
        });
        return view;
    }
}