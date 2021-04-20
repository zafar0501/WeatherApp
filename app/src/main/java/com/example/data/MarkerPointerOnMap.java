package com.example.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarkerPointerOnMap {

    // static variable single_instance of type Singleton
    private static MarkerPointerOnMap single_instance = null;

    public static List<LatLng> getListMap() {
        return listMap;
    }

    public static MarkerPointerOnMap getSingle_instance() {
        return single_instance;
    }

    private static List<LatLng> listMap = null;

    private MarkerPointerOnMap() {
    }

    // static method to create instance of Singleton class
    synchronized public static MarkerPointerOnMap getInstance() {
        if (single_instance == null) {
            single_instance = new MarkerPointerOnMap();
            listMap = new ArrayList<>();
        }

        return single_instance;
    }


}
