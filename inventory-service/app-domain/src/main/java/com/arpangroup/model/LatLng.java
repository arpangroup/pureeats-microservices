package com.arpangroup.model;

public class LatLng {
    private float lat;
    private float lng;

    private LatLng(){//private constructor

    }

    public LatLng(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
