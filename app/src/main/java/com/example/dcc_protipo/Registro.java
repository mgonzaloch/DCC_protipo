package com.example.dcc_protipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void ingreso(View view) {
        Intent ir1 = new Intent(Registro.this,Presentacion.class);
        startActivity(ir1);
    }
}
