package com.nelsonbenitez.cesetencuesta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class UnoActivity extends AppCompatActivity {

    String servicio, calificacion_uno, calificacion_dos,calificacion_tres,calificacion_cuatro, calificacion_cinco, calificacion;
    LottieAnimationView satisfecho, insatisfecho,muy_insatisfecho;
    LottieAnimationView muy_satisfecho, atras, adelante;

    RingProgressBar estadoEncuenta;
    private int progress=10;
    boolean calificacionrealizada=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno);


        Intent informacion= getIntent();
        servicio=informacion.getStringExtra("servicio");
        calificacion_uno=informacion.getStringExtra("calificacion_uno");
        calificacion_dos=informacion.getStringExtra("calificacion_dos");
        calificacion_tres=informacion.getStringExtra("calificacion_tres");
        calificacion_cuatro=informacion.getStringExtra("calificacion_cuatro");
        calificacion_cinco=informacion.getStringExtra("calificacion_cinco");


        estadoEncuenta= (RingProgressBar) findViewById(R.id.progress_bar_1) ;
        estadoEncuenta.setProgress(progress);



        addListenerOnButton();

        if (!calificacion_uno.isEmpty()){
            calificacion=calificacion_uno;

            progress=20;
            seleccionarOpcion(calificacion_uno);
        }

    }

    private void seleccionarOpcion(String opcion_seleccionada) {
        switch (opcion_seleccionada){

            case "Muy Satisfecho":
                muy_satisfecho.setAnimation("check_animation.json");
                muy_satisfecho.playAnimation();
                break;

            case "Satisfecho":
                satisfecho.setAnimation("check_animation.json");
                satisfecho.playAnimation();
                break;


            case "Insatisfecho":
                insatisfecho.setAnimation("check_animation.json");
                insatisfecho.playAnimation();
                break;

            case "Muy Insatisfecho":
                muy_insatisfecho.setAnimation("check_animation.json");
                muy_insatisfecho.playAnimation();
                break;

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void addListenerOnButton() {
        satisfecho = (LottieAnimationView) findViewById(R.id.satisfecho);
        muy_satisfecho = (LottieAnimationView) findViewById(R.id.muy_satisfecho);
        insatisfecho = (LottieAnimationView) findViewById(R.id.Insatisfecho);
        muy_insatisfecho = (LottieAnimationView) findViewById(R.id.muy_insatisfecho);
        adelante = (LottieAnimationView) findViewById(R.id.adelante);
        atras = (LottieAnimationView) findViewById(R.id.atras);

        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calificacionrealizada){
                    Intent next = new Intent(UnoActivity.this,DosActivity.class);
                    next.putExtra("servicio",servicio);
                    next.putExtra("calificacion_uno",calificacion);
                    next.putExtra("calificacion_dos",calificacion_dos);
                    next.putExtra("calificacion_tres",calificacion_tres);
                    next.putExtra("calificacion_cuatro",calificacion_cuatro);
                    next.putExtra("calificacion_cinco",calificacion_cinco);
                    startActivity(next);finish();

                }else{
                    Toast.makeText(UnoActivity.this, "Usted no ha calificado esta pregunta", Toast.LENGTH_SHORT).show();
                }
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(UnoActivity.this,MainActivity.class);
                next.putExtra("servicio",servicio);
                next.putExtra("calificacion_uno",calificacion);
                next.putExtra("calificacion_dos",calificacion_dos);
                next.putExtra("calificacion_tres",calificacion_tres);
                next.putExtra("calificacion_cuatro",calificacion_cuatro);
                next.putExtra("calificacion_cinco",calificacion_cinco);
                startActivity(next);
                finish();
            }
        });


        satisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("check_animation.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.playAnimation();
                insatisfecho.setAnimation("wow.json");
                insatisfecho.playAnimation();
                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.playAnimation();
                progress=20;
                calificacion="Satisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


        muy_satisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("happy.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("check_animation.json");
                muy_satisfecho.playAnimation();
                insatisfecho.setAnimation("wow.json");
                insatisfecho.playAnimation();
                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.playAnimation();
                progress=20;
                calificacion="Muy Satisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


        insatisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("happy.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.playAnimation();
                insatisfecho.setAnimation("check_animation.json");
                insatisfecho.playAnimation();
                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.playAnimation();
                progress=20;
                calificacion="Insatisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


        muy_insatisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("happy.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.playAnimation();
                insatisfecho.setAnimation("wow.json");
                insatisfecho.playAnimation();
                muy_insatisfecho.setAnimation("check_animation.json");
                muy_insatisfecho.playAnimation();
                progress=20;
                calificacion="Muy Insatisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


    }
}
