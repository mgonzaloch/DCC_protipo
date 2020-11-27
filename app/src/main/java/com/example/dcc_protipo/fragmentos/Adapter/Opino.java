package com.example.dcc_protipo.fragmentos.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcc_protipo.R;
import com.example.dcc_protipo.fragmentos.models.modelo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Opino extends Fragment {

    private RecyclerView lista;
    String currentOpinion, currentComentario;
    private DatabaseReference opinoref;


    public Opino(String opinionID,String deID) {
        currentOpinion = opinionID;
        currentComentario = deID;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opino, container, false);


        lista = view.findViewById(R.id.RecyclerInfo);
        lista.setLayoutManager(new LinearLayoutManager(getContext()));


        opinoref = FirebaseDatabase.getInstance().getReference().child("opiniones")
                .child(currentOpinion)
                .child(currentComentario);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<modelo>()
                    .setQuery(opinoref,modelo.class)
                    .build();
        FirebaseRecyclerAdapter<modelo,opinonViewHolder> adapter
                = new FirebaseRecyclerAdapter<modelo, opinonViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull final opinonViewHolder opinonViewHolder, int position, @NonNull modelo modelo)
            {

                final String estableID = getRef(position).getKey();
                opinoref.child(estableID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String nombrede = snapshot.child("nomb").getValue().toString();
                        String opinode = snapshot.child("opin").getValue().toString();

                        opinonViewHolder.autor.setText(nombrede);
                        opinonViewHolder.comentario.setText(opinode);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @NonNull
            @Override
            public opinonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_opinion,parent,false);
                opinonViewHolder viewHolder = new opinonViewHolder(view);
                return  viewHolder;
            }

        };
        lista.setAdapter(adapter);
        adapter.startListening();
    }

    private static class opinonViewHolder extends RecyclerView.ViewHolder
    {
        TextView autor, comentario;

        public opinonViewHolder(@NonNull View itemView) {
            super(itemView);

            autor = itemView.findViewById(R.id.autor);
            comentario = itemView.findViewById(R.id.opinion);

        }
    }
}
