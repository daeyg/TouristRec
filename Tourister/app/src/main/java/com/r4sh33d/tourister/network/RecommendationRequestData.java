package com.r4sh33d.tourister.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommendationRequestData {

    @SerializedName("budget")
    @Expose
    public String budget;
    @SerializedName("zone")
    @Expose
    public String zone;
    @SerializedName("present_location")
    @Expose
    public String presentLocation;

    @Override
    public String toString() {
        return "RecommendationRequestData{" +
                "budget=" + budget +
                ", zone='" + zone + '\'' +
                ", presentLocation='" + presentLocation + '\'' +
                '}';
    }
}
