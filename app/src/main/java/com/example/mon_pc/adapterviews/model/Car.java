package com.example.mon_pc.adapterviews.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by MON-PC on 25/01/2017.
 */

public class Car implements Parcelable {
    public String brand;
    public String model;
    public String color;
    public String type;
    public String img;
//    String json;
//    StringBuilder builder;
    //json = builder.toString();

    public Car(String type, String brand, String model, String color) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Car() {

    }
    public Car(JSONObject json) {
        this.fromJSON(json);
    }

    protected Car(Parcel in) {
        brand = in.readString();
        model = in.readString();
        color = in.readString();
        type = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeString(color);
        dest.writeString(type);
    }
    public void fromJSON(JSONObject json) {
        this.brand = json.optString("brand");
        this.model = json.optString("model");
    }
}
