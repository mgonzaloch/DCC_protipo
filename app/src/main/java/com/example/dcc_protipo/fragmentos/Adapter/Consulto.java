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

    import com.example.dcc_protipo.DetallesC;
    import com.example.dcc_protipo.R;
    import com.example.dcc_protipo.fragmentos.models.modelc;
    import com.firebase.ui.database.FirebaseRecyclerAdapter;
    import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    public class Consulto extends Fragment {

        private RecyclerView lista;

        private DatabaseReference hospitalesref, establecimientosref;
        private FirebaseAuth mAuth;
        private String currentHospitalid;

        public Consulto() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view=inflater.inflate(R.layout.fragment_consulto, container, false);

            lista = view.findViewById(R.id.RecyclerCon);
            lista.setLayoutManager(new LinearLayoutManager(getContext()));

            hospitalesref = FirebaseDatabase.getInstance().getReference().child("establecimientos_hospital");
            establecimientosref = FirebaseDatabase.getInstance().getReference().child("establecimientos");

            return view;
        }

        @Override
        public void onStart()
        {
            super.onStart();
            FirebaseRecyclerOptions options =
                    new FirebaseRecyclerOptions.Builder<modelc>()
                    .setQuery(hospitalesref,modelc.class)
                    .build();

            FirebaseRecyclerAdapter<modelc,hospitalViewHolder> adapter
                    =new FirebaseRecyclerAdapter<modelc, hospitalViewHolder>(options)
            {
                @Override
                protected void onBindViewHolder(@NonNull final hospitalViewHolder hospitalViewHolders, int position, @NonNull modelc modelc)
                {

                    final String estableID = getRef(position).getKey();
                    establecimientosref.child(estableID).addValueEventListener(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot)
                        {
                            String imagende = snapshot.child("foto").getValue().toString();
                            String direccionde = snapshot.child("dire").getValue().toString();
                            String nombrede = snapshot.child("nomb").getValue().toString();

                            final String empresa = snapshot.child("empr").getValue().toString();

                            hospitalViewHolders.nombre.setText(nombrede);
                            hospitalViewHolders.direccion.setText(direccionde);
                            Picasso.get().load(imagende).placeholder(R.drawable.ic_launcher_background).into(hospitalViewHolders.profileimage);

                            hospitalViewHolders.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v)
                                {
                                    Intent detalle = new Intent(getContext(), DetallesC.class);
                                    detalle.putExtra("visit_hospital_id", estableID);
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
                public hospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_con,parent,false);
                    hospitalViewHolder viewHolder = new hospitalViewHolder(view);
                    return viewHolder;
                }
            };
            lista.setAdapter(adapter);
            adapter.startListening();
        }
        private static class hospitalViewHolder extends RecyclerView.ViewHolder
        {
            TextView nombre, direccion;
            ImageView profileimage;

            public hospitalViewHolder(@NonNull View itemView)
            {
                super(itemView);

                nombre = itemView.findViewById(R.id.text_list_titel);
                direccion = itemView.findViewById(R.id.text_list_context);
                profileimage = itemView.findViewById(R.id.imageView);

            }
        }
    }
