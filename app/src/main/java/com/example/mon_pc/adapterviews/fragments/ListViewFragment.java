package com.example.mon_pc.adapterviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.AssetUtil;
import com.example.mon_pc.adapterviews.ListViewAdapter;
import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.model.Car;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    protected List<Car> cars;
    protected ListViewAdapter carsAdapter;
    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__list, container, false);
        this.cars = AssetUtil.loadCars(this.getActivity().getAssets());

        this.carsAdapter = new ListViewAdapter(this.cars);
        //this.carsAdapter.getView()
        Log.d("TEST", "AAAAAAAA");
        ListView carsLV = (ListView) view.findViewById(R.id.mListView);
        carsLV.setAdapter(this.carsAdapter);
        return view;
    }

}
