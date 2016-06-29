package com.samsrutidash.tourguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LocationFullDisplay extends AppCompatActivity {

    public String Name;
//    public double lati ;
//    public double lon;
//    public void setGeo(double a, double b){
//        lati = a;
//        lon = b;
//    }
//    public double getLat(){
//        return lati;
//    }
//    public double getlon(){
//        return lon;
//    }

    public void setName(String s){
        Name = s;
    }
    public String getName(){
        return Name;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_full_display);
        // Get Name, Description, Image views
        TextView name = (TextView)findViewById(R.id.title);
        TextView desc = (TextView)findViewById(R.id.description);
        ImageView img = (ImageView)findViewById(R.id.image);

        // Get data passed in from Fragment
        Intent details = getIntent();
        setTitle(details.getStringExtra("locationName"));
        setName(details.getStringExtra("locationName"));
//      Location Details
//      getGeo(details.putExtra("locationLON", current.getLon()), details.putExtra("locationLAT", current.getLat()));

        name.setText(details.getStringExtra("locationName"));
        desc.setText(details.getStringExtra("locationDesc"));

        final int id = details.getIntExtra("locationIcon",-1);
        if(id != -1) {
            img.setImageResource(id);
        }
        else{
            img.setVisibility(View.GONE);
        }


    }

    public void openGoogleMap(View view){
        // Map point based on address
        String n = getName();
//        Or We can use the lat and lon value to parse
//        double lat = getLat();
//        double lon = getLon();
//        Uri location = Uri.parse("geo:" + lat + "," + lon);
        Uri location = Uri.parse("geo:0,0?q="+n+"+Kuala+Lumpur+Malaysia");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(mapIntent);

    }

    public void openWebsite(){
    //        Here We can add a website button too :)
    }
}

