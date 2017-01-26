package com.example.mon_pc.adapterviews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.R;

public class ListViewActivity extends AppCompatActivity {
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen__list);

        //mListView = (ListView)findViewById(R.id.mListView);

        //final ArrayList<Car> carList = ListViewAdapter.getRecipesFromFile("Cars.json", this);

    }
}
