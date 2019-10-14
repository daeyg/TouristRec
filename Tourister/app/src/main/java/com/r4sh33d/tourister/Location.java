package com.r4sh33d.tourister;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    String state;
    String cost;
    String name;

    public Location(String state, String cost, String name) {
        this.state = state;
        this.cost = cost;
        this.name = name;
    }

    protected Location(Parcel in) {
        state = in.readString();
        cost = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeString(cost);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
