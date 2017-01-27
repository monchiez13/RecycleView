package com.example.mon_pc.adapterviews.fragments;


import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.AssetUtil;
import com.example.mon_pc.adapterviews.ListViewAdapter;
import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {

    protected List<Car> cars;
    protected ListViewAdapter carsAdapter;
    protected List<ListViewFragment.Listener> listeners;

    public ListViewFragment() {
        // Required empty public constructor

        this.listeners = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //FragmentActivity mactivity = getActivity();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__list, container, false);
        this.cars = AssetUtil.loadCars(this.getActivity().getAssets());

        this.carsAdapter = new ListViewAdapter(this.cars);
        //this.carsAdapter.getView()
        Log.d("TEST", "AAAAAAAA");
        ListView carsLV = (ListView) view.findViewById(R.id.mListView);
        carsLV.setAdapter(this.carsAdapter);
        this.bootstrapViews(view);

        return view;
    }

    protected void bootstrapViews(View view) {
        ListView cars;

        cars = (ListView)view.findViewById(R.id.mListView);
        cars.setAdapter(this.carsAdapter);
        cars.setOnItemClickListener(new ItemClickListener());
    }

    protected void fireSelectionEvent(Car car) {
        for (Listener listener : this.listeners)
            listener.onCarSelected(car);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    protected class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> source, View view, int position, long itemId) {
            ListViewFragment self = ListViewFragment.this;

            self.fireSelectionEvent(self.cars.get(position));
        }
    }

    public interface Listener {
            void onCarSelected(Car car);
//        public void addListener();
//        public void onBookSelected(Book book);

    }



}
