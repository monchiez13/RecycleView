package com.example.mon_pc.adapterviews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.fragments.ListViewFragment;
import com.example.mon_pc.adapterviews.model.Car;

public class ListViewActivity extends AppCompatActivity {
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen__list);
        ListViewFragment fragment;

        fragment = (ListViewFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.addListener(new FragmentListener());
        //mListView = (ListView)findViewById(R.id.mListView);

        //final ArrayList<Car> carList = ListViewAdapter.getRecipesFromFile("Cars.json", this);

    }

    protected class FragmentListener implements ListViewFragment.Listener {
        @Override
        public void onCarSelected(Car car) {
            ListViewActivity self = ListViewActivity.this;
            Bundle extras;

            extras = new Bundle();
            extras.putParcelable("car", car);

            self.startActivity(
                    new Intent(self, DetailActivity.class)
                            .putExtras(extras)
            );
        }
    }
}
