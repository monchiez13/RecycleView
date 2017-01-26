package com.example.mon_pc.adapterviews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.model.Car;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {


    protected List<Car> cars;
    protected RecycleViewAdapter carsAdapter;
    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);


        this.cars = AssetUtil.loadCars(this.getActivity().getAssets());

        this.carsAdapter = new RecycleViewAdapter(this.cars);
        //this.carsAdapter.onCreateViewHolder()
        //Log.d("TEST", "AAAAAAAA");
        RecyclerView carsLV = (RecyclerView) view.findViewById(R.id.recyclecars);
        //carsLV.hasFixedSize(true);
        carsLV.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        carsLV.setAdapter(this.carsAdapter);
        return view;
    }

}
