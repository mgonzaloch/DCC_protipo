package com.example.dcc_protipo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        int DURATION_SPLASH = 400;
        new Handler().postDelayed(new Runnable() {
            public void run(){
                Intent ir = new Intent(MainActivity.this, Login.class);
                startActivity(ir);
                finish();
            }
        }, DURATION_SPLASH);
        setContentView(R.layout.activity_main);

    }
}
