package com.r4sh33d.tourister.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatesWithCityListWrapper {

    @SerializedName("data")
    @Expose
    public ArrayList<StateWithCityLOVModel> statesWithCities;

}