package com.example.dcc_protipo.fragmentos.Adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcc_protipo.DetallesP;
import com.example.dcc_protipo.R;
import com.example.dcc_protipo.fragmentos.models.modelp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.

 */
public class Medicamento extends Fragment {

    private RecyclerView lista;

    private String tiproducto = "medicamentos";

    private DatabaseReference medicamentosref,productossref;

    public Medicamento(String tipProduct) {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicamento, container, false);

        lista = view.findViewById(R.id.RecyclerInfo);
        lista.setLayoutManager(new LinearLayoutManager(getContext()));

        medicamentosref = FirebaseDatabase.getInstance().getReference().child("productos_medicamentos");
        productossref = FirebaseDatabase.getInstance().getReference().child("productos");

        return view;
    }

    @Override
    public void onStart()
    {
        Toast.makeText(getActivity(),tiproducto ,Toast.LENGTH_SHORT).show();
        super.onStart();
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<modelp>()
                        .setQuery(medicamentosref,modelp.class)
                        .build();

        FirebaseRecyclerAdapter<modelp, Info.productosViewHolder> adapter
                =new FirebaseRecyclerAdapter<modelp, Info.productosViewHolder>(options)

        {
            @Override
            protected void onBindViewHolder(@NonNull final Info.productosViewHolder productosViewHolder, int position, @NonNull modelp modelp)
            {
                final String estableIn = getRef(position).getKey();
                productossref.child(estableIn).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String categoriade = snapshot.child("cate").getValue().toString();
                        String imagende = snapshot.child("foto").getValue().toString();
                        String preciode = snapshot.child("prec").getValue().toString();
                        String nombrede = snapshot.child("nomb").getValue().toString();

                        final String empresa = snapshot.child("empr").getValue().toString();

                        productosViewHolder.nombre.setText(nombrede);
                        productosViewHolder.precio.setText("$"+preciode);
                        Picasso.get().load(imagende).placeholder(R.drawable.ic_launcher_background).into(productosViewHolder.profileimage);

                        productosViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                Intent detalle = new Intent(getContext(), DetallesP.class);
                                detalle.putExtra("visit_producto_id", estableIn);
                                detalle.putExtra("visit_empresa_id", empresa);
                                startActivity(detalle);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public Info.productosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_inf,parent,false);
                Info.productosViewHolder viewHolder = new Info.productosViewHolder(view);
                return viewHolder;
            }
        };
        lista.setAdapter(adapter);
        adapter.startListening();
    }


    public static class productosViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre, precio;
        ImageView profileimage;

        public productosViewHolder(@NonNull View intemView)
        {
            super(intemView);

            nombre = intemView.findViewById(R.id.tv_nombre);
            precio = intemView.findViewById(R.id.tv_context);
            profileimage = intemView.findViewById(R.id.iv_foto);

        }
    }
}