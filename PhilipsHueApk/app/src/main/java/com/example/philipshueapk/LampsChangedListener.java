package com.example.philipshueapk;

import com.example.philipshueapk.lamp.LampProduct;

import java.util.ArrayList;

/**
 * Interface to update the list of lights
 */
public interface LampsChangedListener {
    void onLampsChanged(ArrayList<LampProduct> lamps) ;
}
