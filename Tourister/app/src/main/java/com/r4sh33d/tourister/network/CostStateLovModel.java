package com.r4sh33d.tourister.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CostStateLovModel {
    @SerializedName("cost")
    @Expose
    public String cost;
    @SerializedName("state")
    @Expose
    public String state;
}
