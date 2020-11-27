package com.example.dcc_protipo.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcc_protipo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mapas extends Fragment {

    public Mapas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapas, container, false);
    }
}
