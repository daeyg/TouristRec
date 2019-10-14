package com.r4sh33d.tourister.network;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeyTitleLovModel implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("key")
    @Expose
    public String key;

    public KeyTitleLovModel() {
    }

    public KeyTitleLovModel(String id, String name, String key) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    protected KeyTitleLovModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KeyTitleLovModel> CREATOR = new Creator<KeyTitleLovModel>() {
        @Override
        public KeyTitleLovModel createFromParcel(Parcel in) {
            return new KeyTitleLovModel(in);
        }

        @Override
        public KeyTitleLovModel[] newArray(int size) {
            return new KeyTitleLovModel[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }


}
