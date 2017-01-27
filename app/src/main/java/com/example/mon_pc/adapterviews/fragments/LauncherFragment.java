package com.example.mon_pc.adapterviews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mon_pc.adapterviews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LauncherFragment extends Fragment {

    protected List<Listener> listeners;
    public LauncherFragment() {
        // Required empty public constructor
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    protected void fireListRequest() {
        for (Listener listener : this.listeners)
            listener.onLstClicked();
    }

    protected void fireRecyclerRequest() {
        for (Listener listener : this.listeners)
            listener.onRycClicked();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_launcher, container, false);
        ClickListener listener = new ClickListener();
        root.findViewById(R.id.btnlst).setOnClickListener(listener);
        root.findViewById(R.id.btnryc).setOnClickListener(listener);



        return root;
    }

    protected class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View source) {
            LauncherFragment self = LauncherFragment.this;

            if (source.getId() == R.id.btnlst)
                self.fireListRequest();
            else if (source.getId() == R.id.btnryc)
                self.fireRecyclerRequest();
        }
    }

    public interface Listener {
        void onLstClicked();
        void onRycClicked();
    }

}
