package com.r4sh33d.tourister.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StateWithCityLOVModel {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("cities")
    @Expose
    public ArrayList<KeyTitleLovModel> cities;

    @Override
    public String toString() {
        return name;
    }
}
