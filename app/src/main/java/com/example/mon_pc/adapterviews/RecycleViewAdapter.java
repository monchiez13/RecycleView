package com.example.mon_pc.adapterviews;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.*;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mon_pc.adapterviews.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MON-PC on 25/01/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    protected List<Car> cars;
    protected List<Car> carscpy;
    protected List<Listener> listeners;


    public RecycleViewAdapter(Context context, List<Car> cars) {
        inflater = LayoutInflater.from(context);
        this.cars = cars;
        this.carscpy = cars;
        listeners = new ArrayList<>();
    }

    public void filter(String text) {
        cars.clear();
        if(text.isEmpty()){
            cars.addAll(carscpy);
        } else{
            text = text.toLowerCase();
            for(Car item: carscpy ){
                if(item.brand.toLowerCase().contains(text) || item.model.toLowerCase().contains(text)){
                    cars.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }



    public RecycleViewAdapter(List<Car> cars) {
        super();
        if (cars ==null) {
            Log.d("ArrayAdapter","NULL CARS");
            cars = new ArrayList<>();


        }
        carscpy = new ArrayList<>();
        this.cars = cars;
        carscpy.addAll(this.cars);
        listeners = new ArrayList<>();
        Log.d("ArrayAdapter","ArrayAdapter");
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          Log.d("BINDING", "CALL RECYCLEROW");
            Log.d("SIZE", String.valueOf(cars.size()));
//        Context context = parent.getContext();
//        inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.recyclerow, parent, false);
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
        return new MyViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter__recycler__car, parent, false));


    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    protected void fireSelectionEvent(View view) {
        for (Listener listener : this.listeners)
            listener.onItemSelected(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Resources res = holder.itemView.getResources();
        Car car = cars.get(position);
        holder.brand.setText(car.brand);
        holder.model.setText(car.model);

    }

    @Override
    public int getItemCount() {
        Log.d("SIZE", String.valueOf(cars.size()));
        return cars.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView brand,model;

        protected RecycleViewAdapter adapter;
        public MyViewHolder(RecycleViewAdapter adapter, View item) {
            super(item);

            this.brand = (TextView) item.findViewById(R.id.txtBrand);
            this.model = (TextView) item.findViewById(R.id.txtModel);
            this.adapter = adapter;

            //this.adapter = adapter;

            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.adapter.fireSelectionEvent(this.itemView);
        }
    }

    public interface Listener {
        void onItemSelected(View view);
    }
}
