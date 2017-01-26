package com.example.mon_pc.adapterviews;

import android.content.Context;
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


    public RecycleViewAdapter(Context context, List<Car> cars) {
        inflater = LayoutInflater.from(context);
        this.cars = cars;
    }


    public RecycleViewAdapter(List<Car> cars) {
        super();
        if (cars ==null) {
            Log.d("ArrayAdapter","NULL CARS");
            cars = new ArrayList<>();
        }
        this.cars = cars;
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

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.brand.setText(car.brand);
        holder.model.setText(car.model);

    }

    @Override
    public int getItemCount() {
        Log.d("SIZE", String.valueOf(cars.size()));
        return cars.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView brand,model;

        protected RecycleViewAdapter adapter;
        public MyViewHolder(RecycleViewAdapter adapter,View itemView) {
            super(itemView);

            this.brand = (TextView) itemView.findViewById(R.id.txtBrand);
            this.model = (TextView) itemView.findViewById(R.id.txtModel);
            this.adapter = adapter;

        }
    }
}
