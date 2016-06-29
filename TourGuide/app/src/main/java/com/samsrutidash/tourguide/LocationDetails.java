package com.samsrutidash.tourguide;

/**
 * Created by samsrutidash on 6/29/2016.
 */
public class LocationDetails {
    private String locationName;
    private String locationDesc;
    private int locationIcon;
    private double lat;
    private double lon;

    public LocationDetails(){
        super();
    }

    public String getLocationName(){

        return this.locationName;
    }
    public int getLocationIcon() {
        return  this.locationIcon;
    }

    public String getLocationDesc(){

        return this.locationDesc;
    }
    public double getLat(){

        return this.lat;
    }
    public double getLon(){

        return this.lon;
    }

    public LocationDetails(String locationName, String locationDesc, int locationIcon,
                            double lat, double lon ) {
        super();
        this.locationName = locationName;
        this.locationDesc = locationDesc;
        this.locationIcon= locationIcon;
        this.lat= lat;
        this.lon= lon;
    }

    @Override
    public String toString() {

        return locationName+" "+locationDesc+" "+locationIcon ;
    }
}


