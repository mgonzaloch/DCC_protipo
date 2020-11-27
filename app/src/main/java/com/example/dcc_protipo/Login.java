package com.example.dcc_protipo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "tag" ;

    private EditText txtcorreo, txtcontrasena;

     private FirebaseAuth mAuth;
     private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        txtcorreo = (EditText)findViewById(R.id.editText);
        txtcontrasena = (EditText)findViewById(R.id.RContra);

    }

    public void ingreso(View view) {

         String correo = txtcorreo.getText().toString();
         String contrasena = txtcontrasena.getText().toString();


        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contrasena))
         {

             mAuth.signInWithEmailAndPassword(correo, contrasena)
                     .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 // Sign in success, update UI with the signed-in user's information
                                 Log.d(TAG, "signInWithEmail:success");
                                 FirebaseUser user = mAuth.getCurrentUser();

                                 Intent ir1 = new Intent(Login.this,Presentacion.class);
                                 startActivity(ir1);

                             } else {
                                 // If sign in fails, display a message to the user.
                                 Log.w(TAG, "signInWithEmail:failure", task.getException());
                                 Toast.makeText(Login.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();

                             }

                             // ...
                         }
                     });

         }
         else
         {
             Toast.makeText(this,"Ingrese datos", Toast.LENGTH_LONG).show();
         }

    }


    public void registro(View view) {
        Intent ir1 = new Intent(Login.this,Registro.class);
        startActivity(ir1);
    }
}