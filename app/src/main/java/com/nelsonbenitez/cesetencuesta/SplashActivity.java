package com.nelsonbenitez.cesetencuesta;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent uno = new Intent().setClass(SplashActivity.this,MainActivity.class);
                uno.putExtra("servicio"," ");
                uno.putExtra("calificacion_uno"," ");
                uno.putExtra("calificacion_dos"," ");
                uno.putExtra("calificacion_tres"," ");
                uno.putExtra("calificacion_cuatro"," ");
                uno.putExtra("calificacion_cinco"," ");
                startActivity(uno);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(task,SPLASH_DELAY);
    }
}
