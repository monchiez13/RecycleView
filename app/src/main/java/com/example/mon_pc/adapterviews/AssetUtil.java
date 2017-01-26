package com.example.mon_pc.adapterviews;

import android.content.res.AssetManager;

import com.example.mon_pc.adapterviews.model.Car;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MON-PC on 25/01/2017.
 */

public class AssetUtil {
    public AssetUtil() {}



    public static List<Car> loadCars(AssetManager manager) {
        BufferedReader reader;
        StringBuilder builder;
        String line;
        String json;
        JSONArray data;

        try {
            reader = new BufferedReader(new InputStreamReader(manager.open("cars.json")));
            builder = new StringBuilder();

            while ((line = reader.readLine()) != null)
                builder.append(line);

            json = builder.toString();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }

        try {
            data = new JSONArray(json);
        }
        catch (JSONException exception) {
            return new ArrayList<>();
        }

        List<Car> output = new ArrayList<>();

        for (int index = 0; index < data.length(); index++)
            output.add(new Car(data.optJSONObject(index)));

        return output;
    }
}
