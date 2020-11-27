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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DPInformacion extends Fragment {

    private DatabaseReference empresasref, productoref;
    private String currentEmpresa, currentEstable;
    TextView nombre ,direccion,empresa,disponible,precio,delivery;
    ImageView profileimage;

    public DPInformacion(String empresaID,String productoID) {
        currentEmpresa = empresaID;
        currentEstable = productoID;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_d_p_informacion, container, false);
        nombre = view.findViewById(R.id.textView7);
        empresa = view.findViewById(R.id.textView20);
        precio = view.findViewById(R.id.textView21);
        delivery = view.findViewById(R.id.textView10);
        disponible = view.findViewById(R.id.textView16);
        profileimage = view.findViewById(R.id.imageView2);

        empresasref = FirebaseDatabase.getInstance().getReference().child("empresas").child(currentEmpresa);
        productoref = FirebaseDatabase.getInstance().getReference().child("productos").child(currentEstable);

        productoref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String nombrede = snapshot.child("nomb").getValue().toString();
                String preciode = snapshot.child("prec").getValue().toString();
                String deliveryde = snapshot.child("envi").getValue().toString();
                String disponiblede = snapshot.child("disp").getValue().toString();

                nombre.setText(nombrede);
                precio.setText("/$" + preciode);
                delivery.setText("me"+deliveryde);
                disponible.setText("me" + disponiblede);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        empresasref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String imagende = snapshot.child("foto").getValue().toString();
                String empresade = snapshot.child("nomb").getValue().toString();

                empresa.setText(empresade);
                Picasso.get().load(imagende).placeholder(R.drawable.ic_launcher_background).fit().into(profileimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}