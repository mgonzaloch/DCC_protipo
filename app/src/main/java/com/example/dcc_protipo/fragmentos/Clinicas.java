package com.example.dcc_protipo.fragmentos;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcc_protipo.R;
import com.example.dcc_protipo.fragmentos.Adapter.Consulto;
import com.example.dcc_protipo.fragmentos.Adapter.Info;
import com.example.dcc_protipo.fragmentos.Adapter.Medicamento;
import com.example.dcc_protipo.fragmentos.Adapter.Opino;
import com.example.dcc_protipo.fragmentos.Adapter.Oxigeno;
import com.example.dcc_protipo.fragmentos.Adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Clinicas extends Fragment {

    View myFragment;

    ViewPager viewpager;
    TabLayout tablayout;
    public Clinicas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_clinicas, container, false);

        viewpager =  myFragment.findViewById(R.id.viewpager);
        tablayout =  myFragment.findViewById(R.id.tablayout);

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewpager);
        tablayout.setupWithViewPager(viewpager);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setUpViewPager(ViewPager viewpager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new Consulto(), "Clinicas");
        adapter.addFragment(new Medicamento("Medicamentos"), "Medicinas");
        adapter.addFragment(new Oxigeno(), "OXigeno");
        adapter.addFragment(new Info(), "Otros");

        viewpager.setAdapter(adapter);
    }
}
