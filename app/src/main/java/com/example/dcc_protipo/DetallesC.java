package com.example.dcc_protipo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dcc_protipo.fragmentos.Adapter.Opino;
import com.example.dcc_protipo.fragmentos.Clinicas;
import com.example.dcc_protipo.fragmentos.DCInformacion;
import com.example.dcc_protipo.fragmentos.Mapas;
import com.example.dcc_protipo.fragmentos.Perfil;
import com.example.dcc_protipo.fragmentos.Productos;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetallesC extends AppCompatActivity {

    String globalID = "prueba";
    private String messageEstablecimientoID,messageEmpresaID;
    private DatabaseReference empresasref;
    ImageView profileimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_c);

        messageEstablecimientoID = getIntent().getExtras().get("visit_hospital_id").toString();
        messageEmpresaID = getIntent().getExtras().get("visit_empresa_id").toString();

        Toast.makeText(DetallesC.this, messageEmpresaID, Toast.LENGTH_SHORT).show();

        profileimage = findViewById(R.id.portada);

        empresasref = FirebaseDatabase.getInstance().getReference().child("empresas").child(messageEmpresaID);

        empresasref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imagende = snapshot.child("foto").getValue().toString();
                Picasso.get().load(imagende).placeholder(R.drawable.ic_launcher_background).fit().into(profileimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.funciona,
                new DCInformacion(messageEmpresaID,messageEstablecimientoID)).commit();

    }
    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.infomracion:
                            selectedFragment = new DCInformacion(messageEmpresaID,messageEstablecimientoID);
                            break;
                        case R.id.opiniones:
                            selectedFragment = new Opino("establecimientos",messageEstablecimientoID);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.funciona,
                            selectedFragment).commit();
                    return true;
                }
            };
}