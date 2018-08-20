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

        //Toast.makeText(this, "Se recibe "+calificacion_uno+" en UNO", Toast.LENGTH_SHORT).show();


        addListenerOnButton();
        seleccionarOpcion(calificacion_uno);


    }

    private void seleccionarOpcion(String opcion_seleccionada) {
        switch (opcion_seleccionada){

            case "Muy Satisfecho":
                muy_satisfecho.setAnimation("success.json");
                muy_satisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;
                break;

            case "Satisfecho":
                satisfecho.setAnimation("check_mark.json");
                satisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;
                break;


            case "Insatisfecho":
                insatisfecho.setAnimation("check.json");
                insatisfecho.setScaleY((float) 1.5);
                insatisfecho.setScaleX((float) 1.5);
                insatisfecho.setRepeatCount(20000);
                insatisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;

                break;

            case "Muy Insatisfecho":
                muy_insatisfecho.setAnimation("check_animation.json");
                muy_insatisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;
                break;


            default:
                calificacionrealizada=false;
                calificacion=opcion_seleccionada;
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
        insatisfecho.setRepeatCount(20000);


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
                satisfecho.setAnimation("check_mark.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.playAnimation();

                insatisfecho.setRepeatCount(0);
                insatisfecho.cancelAnimation();
                insatisfecho.setAnimation("decline_arrows.json");
                //insatisfecho.playAnimation();
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                insatisfecho.setPadding(0,50,0,5);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
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
                muy_satisfecho.setAnimation("success.json");
                muy_satisfecho.playAnimation();

                insatisfecho.setRepeatCount(0);
                insatisfecho.cancelAnimation();
                insatisfecho.setAnimation("decline_arrows.json");
                //insatisfecho.playAnimation();
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                insatisfecho.setPadding(0,50,0,5);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
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


                insatisfecho.setPadding(0,25,0,15);
                insatisfecho.setScaleY((float) 1.5);
                insatisfecho.setScaleX((float) 1.5);
                insatisfecho.setAnimation("check.json");
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
                insatisfecho.setRepeatCount(0);
                insatisfecho.cancelAnimation();
                insatisfecho.setAnimation("decline_arrows.json");
                //insatisfecho.playAnimation();
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                insatisfecho.setPadding(0,50,0,5);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
                insatisfecho.playAnimation();


                satisfecho.setAnimation("happy.json");
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.playAnimation();


                muy_insatisfecho.setScaleX(1);
                muy_insatisfecho.setScaleY(1);
                //muy_insatisfecho.setPadding(0,100,0,5);
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
