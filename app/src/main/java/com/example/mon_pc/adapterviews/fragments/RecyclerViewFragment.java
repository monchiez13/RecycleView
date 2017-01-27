package com.example.mon_pc.adapterviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.AssetUtil;
import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.RecycleViewAdapter;
import com.example.mon_pc.adapterviews.SpacingDecoration;
import com.example.mon_pc.adapterviews.model.Car;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {


    protected List<Listener> listeners;

    protected List<Car> cars;
    protected RecycleViewAdapter carsAdapter;
    public RecyclerViewFragment() {
        // Required empty public constructor
        super();

        this.listeners = new ArrayList<>();
    }


/*    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.mi_search);
        searchView = (SearchView) mSearchMenuItem.getActionView();

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView carsLV;
        this.cars = AssetUtil.loadCars(this.getActivity().getAssets());

        this.carsAdapter = new RecycleViewAdapter(this.cars);
        this.carsAdapter.addListener(new AdapterListener());


        carsLV = (RecyclerView) view.findViewById(R.id.recyclecars);
        carsLV.setHasFixedSize(true);
        carsLV.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        carsLV.addItemDecoration(new SpacingDecoration(this.getActivity(), 5, SpacingDecoration.POLICY_INCLUDE_EDGES_ALL));

        carsLV.setAdapter(this.carsAdapter);
        //MenuItem searchItem = view.findViewById(R)
        SearchView searchView = (SearchView) view.findViewById(R.id.search_bar);


/*        SEARCH FILTER*//*
*/
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                carsAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                carsAdapter.filter(newText);
                return false;
            }
        });


        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //carsAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //carsAdapter.filter(newText);
                return false;
            }
        });
*/
        return view;
    }
    protected class AdapterListener implements RecycleViewAdapter.Listener {
        @Override
        public void onItemSelected(View itemView) {
            RecyclerViewFragment self = RecyclerViewFragment.this;
            RecyclerView list = (RecyclerView)self.getView().findViewById(R.id.recyclecars);

            int position = list.getChildAdapterPosition(itemView);

            self.fireSelectionEvent(self.cars.get(position));
        }
    }


    protected void fireSelectionEvent(Car car) {
        for (Listener listener : this.listeners)
            listener.onCarSelected(car);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public interface Listener {
        void onCarSelected(Car car);
    }


}
