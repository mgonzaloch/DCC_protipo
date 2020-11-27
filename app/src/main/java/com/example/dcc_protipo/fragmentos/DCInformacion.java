package com.example.dcc_protipo.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dcc_protipo.R;
import com.example.dcc_protipo.fragmentos.Adapter.Consulto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass
 */
public class DCInformacion extends Fragment {

    private DatabaseReference empresasref, establecimientosref;
    private FirebaseAuth mAuth;
    private String currentHospitalid, currentEstable;
    TextView nombre,correo ,direccion;
    ImageView profileimage;

    public DCInformacion(String empresaID,String establecimientoID) {
        currentHospitalid = empresaID;
        currentEstable = establecimientoID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_c_informacion, container, false);
        mAuth = FirebaseAuth.getInstance();

        nombre = view.findViewById(R.id.txtnombre);
        correo = view.findViewById(R.id.txtcorreo);
        direccion = view.findViewById(R.id.txtdireccion);
        profileimage = view.findViewById(R.id.imageView2);

        empresasref = FirebaseDatabase.getInstance().getReference().child("empresas").child(currentHospitalid);
        establecimientosref = FirebaseDatabase.getInstance().getReference().child("establecimientos").child(currentEstable);
        establecimientosref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String imagende = snapshot.child("foto").getValue().toString();
                String direccionde = snapshot.child("dire").getValue().toString();
                String nombrede = snapshot.child("nomb").getValue().toString();
                String correode = snapshot.child("mail").getValue().toString();

                nombre.setText(nombrede);
                direccion.setText(direccionde);
                correo.setText(correode);
                Picasso.get().load(imagende).placeholder(R.drawable.ic_launcher_background).fit().into(profileimage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}