package com.example.dcc_protipo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dcc_protipo.fragmentos.Clinicas;
import com.example.dcc_protipo.fragmentos.Mapas;
import com.example.dcc_protipo.fragmentos.Perfil;
import com.example.dcc_protipo.fragmentos.Productos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Presentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                new Clinicas()).commit();
    }


    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.Clinica:
                            selectedFragment = new Clinicas();
                            break;
                        case R.id.Mapa:
                            selectedFragment = new Mapas();
                            break;
                        case R.id.Empresa:
                            selectedFragment = new Productos();
                            break;
                        case R.id.Perfil:
                            selectedFragment = new Perfil();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                            selectedFragment).commit();
                    return true;
                }
            };
}
