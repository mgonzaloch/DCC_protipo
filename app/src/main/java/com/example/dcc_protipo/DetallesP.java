package com.example.dcc_protipo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.dcc_protipo.fragmentos.Adapter.Opino;
import com.example.dcc_protipo.fragmentos.DCInformacion;
import com.example.dcc_protipo.fragmentos.DPInformacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetallesP extends AppCompatActivity {

    private String messageProductoID,messageEmpresaID;
    private DatabaseReference productoref;
    ImageView profileimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_p);

        messageProductoID = getIntent().getExtras().get("visit_producto_id").toString();
        messageEmpresaID = getIntent().getExtras().get("visit_empresa_id").toString();

        profileimage = findViewById(R.id.portada);

        productoref = FirebaseDatabase.getInstance().getReference().child("productos").child(messageProductoID);

        productoref.addValueEventListener(new ValueEventListener() {
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
                new DPInformacion( messageEmpresaID,messageProductoID)).commit();
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.infomracion:
                            selectedFragment = new DPInformacion(messageEmpresaID,messageProductoID);
                            break;
                        case R.id.opiniones:
                            selectedFragment = new Opino("productos",messageProductoID);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.funciona,
                            selectedFragment).commit();
                    return true;
                }
            };
}