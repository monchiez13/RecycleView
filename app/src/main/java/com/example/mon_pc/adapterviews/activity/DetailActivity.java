package com.example.mon_pc.adapterviews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.fragments.DetailFragment;
import com.example.mon_pc.adapterviews.model.Car;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Bundle extras = this.getIntent().getExtras();
        DetailFragment fragment;

        fragment = (DetailFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment2);
        fragment.setCar((Car)extras.getParcelable("car"));
        fragment.refresh();
    }
}
