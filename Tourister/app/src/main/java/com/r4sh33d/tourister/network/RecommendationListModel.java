package com.r4sh33d.tourister.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class RecommendationListModel {

    @SerializedName("available locations")
    @Expose
    public HashMap<String, CostStateLovModel> availableLocations;
    @SerializedName("other recommendations")
    @Expose
    public HashMap<String, CostStateLovModel> otherRecommendations;

}
