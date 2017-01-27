package com.example.mon_pc.adapterviews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.mon_pc.adapterviews.R;
import com.example.mon_pc.adapterviews.fragments.LauncherFragment;

public class MainActivity extends AppCompatActivity {

    Button buttonlst, buttonryc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        buttonlst = (Button) findViewById(R.id.button);
//        buttonryc = (Button) findViewById(R.id.button2);

        LauncherFragment fragment;

        fragment = (LauncherFragment)this.getSupportFragmentManager().findFragmentById(R.id.content_launcher);
        fragment.addListener(new FragmentListener());
    }


    protected class FragmentListener implements LauncherFragment.Listener {
        @Override
        public void onLstClicked() {
            MainActivity.this.startActivity(new Intent(
                    MainActivity.this,
                    ListViewActivity.class
            ));
        }

        public void onRycClicked() {
            MainActivity.this.startActivity(new Intent(
                    MainActivity.this,
                    RecyclerViewActivity.class
            ));
        }
    }
}
