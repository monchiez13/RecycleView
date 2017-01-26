package com.example.mon_pc.adapterviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mon_pc.adapterviews.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MON-PC on 25/01/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Car> Source;
    protected List<Car> cars;

    public ListViewAdapter(List<Car> cars) {
        super();
        if (cars ==null) {
            cars = new ArrayList<>();
        }
        this.cars = cars;
        Log.d("ArrayAdapter","ArrayAdapter");
    }


    @Override
    public int getCount() {
        return this.cars.size();
    }

    @Override
    public Car getItem(int position) {

        return this.cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView","getView");
        Context context = parent.getContext();
        convertView = LayoutInflater.from(context).inflate(R.layout.adapter__list__car, parent, false);
       //convertView = mInflater.inflate(R.layout.adapter__list__car, parent, false);

        ViewHolder holder =new ViewHolder();
        holder.model = (TextView)convertView.findViewById(R.id.textView1);
        holder.brand = (TextView)convertView.findViewById(R.id.textView);

        convertView.setTag(holder);

        holder = (ViewHolder)convertView.getTag();

        Car car = this.getItem(position);
        holder.model.setText(car.model);
        holder.brand.setText(car.brand);
        return convertView;
    }

    public static class ViewHolder {
        public TextView model;
        public TextView brand;
    }



}
