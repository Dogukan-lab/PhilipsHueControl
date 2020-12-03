package com.example.philipshueapk;

import com.example.philipshueapk.lamp.LampProduct;

import java.util.ArrayList;

public interface LampsChangedListener {
    void onLampsChanged(ArrayList<LampProduct> lamps) ;
}
