package com.example.mon_pc.adapterviews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.fragments.RecyclerViewFragment;
import com.example.mon_pc.adapterviews.model.Car;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerViewFragment fragment;

        fragment = (RecyclerViewFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment3);
        fragment.addListener(new FragmentListener());
    }

    protected class FragmentListener implements RecyclerViewFragment.Listener {
        @Override
        public void onCarSelected(Car car) {
            RecyclerViewActivity self = RecyclerViewActivity.this;
            Bundle extras;

            extras = new Bundle();
            extras.putParcelable("car", car);

            self.startActivity(new Intent(self, DetailActivity.class).putExtras(extras));
        }
    }
}
