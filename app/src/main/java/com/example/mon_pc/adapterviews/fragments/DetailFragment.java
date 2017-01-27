package com.example.mon_pc.adapterviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.model.Car;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    protected Car car;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void refresh(View view) {
        if (view == null)
            view = this.getView();

        if (view == null)
            return;

        ((TextView)view.findViewById(R.id.txtViewDetail)).setText(this.car.model);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public void refresh() {
        this.refresh(null);
    }
}
