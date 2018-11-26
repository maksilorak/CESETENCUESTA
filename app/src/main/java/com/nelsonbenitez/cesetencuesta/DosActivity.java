package com.nelsonbenitez.cesetencuesta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class DosActivity extends AppCompatActivity {

    String relacion,servicio, calificacion_uno, calificacion_dos,calificacion_tres,calificacion_cuatro, calificacion_cinco, calificacion;
    LottieAnimationView satisfecho, insatisfecho,muy_insatisfecho;
    LottieAnimationView muy_satisfecho, atras, adelante;
    CircleImageView service;

    RingProgressBar estadoEncuenta;
    private int progress=30;
    boolean calificacionrealizada=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);


        Intent informacion= getIntent();
        servicio=informacion.getStringExtra("servicio");
        calificacion_uno=informacion.getStringExtra("calificacion_uno");
        calificacion_dos=informacion.getStringExtra("calificacion_dos");
        calificacion_tres=informacion.getStringExtra("calificacion_tres");
        calificacion_cuatro=informacion.getStringExtra("calificacion_cuatro");
        calificacion_cinco=informacion.getStringExtra("calificacion_cinco");
        relacion=informacion.getStringExtra("relacion_universidad");


        //Toast.makeText(this, "Recibí como evaluación cuatro en dos "+calificacion_cuatro, Toast.LENGTH_SHORT).show();

        estadoEncuenta= (RingProgressBar) findViewById(R.id.progress_bar_2) ;
        estadoEncuenta.setProgress(progress);

        //Toast.makeText(this, "Se recibe "+calificacion_uno+" en DOS", Toast.LENGTH_SHORT).show();



        service = (CircleImageView) findViewById(R.id.service);
        identificaServicioEvaluado(service,servicio);
        addListenerOnButton();

        seleccionarOpcion(calificacion_dos);


    }

    private void identificaServicioEvaluado(CircleImageView servicio_eval, String servicio) {
        if (servicio.equals("Egresados")){
            servicio_eval.setImageResource(R.drawable.egresados);
        }

        else if (servicio.equals("Educación Continua")){
            servicio_eval.setImageResource(R.drawable.educacion);
        }

        else if (servicio.equals("Mercadeo y Comunicaciones")){
            servicio_eval.setImageResource(R.drawable.mercadeo);
        }

        else if (servicio.equals("Emprendimiento e Innovación")){
            servicio_eval.setImageResource(R.drawable.emprendimiento);
        }

        else if (servicio.equals("Asesoría y Consultoría")){
            servicio_eval.setImageResource(R.drawable.asesoria);
        }

        else if (servicio.equals("Prácticas Académicas")){
            servicio_eval.setImageResource(R.drawable.practicas);
        }

        else if (servicio.equals("Grupo ISO")){
            servicio_eval.setImageResource(R.drawable.iso);
        }
        else if (servicio.equals("Logística")){
            servicio_eval.setImageResource(R.drawable.logistics);
        }

        else if (servicio.equals("Atención al Usuario")){
            servicio_eval.setImageResource(R.drawable.usuario);
        }
    }


    private void seleccionarOpcion(String opcion_seleccionada) {
        switch (opcion_seleccionada){

            case "Muy Satisfecho":
                muy_satisfecho.setAnimation("check_animation.json");
                muy_satisfecho.setRepeatCount(0);
                muy_satisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;
                break;

            case "Satisfecho":
                satisfecho.setAnimation("check_animation.json");
                satisfecho.setRepeatCount(0);
                satisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;
                break;


            case "Insatisfecho":
                insatisfecho.setAnimation("check_animation.json");
                insatisfecho.setPadding(0,0,0,20);
                insatisfecho.setScaleY((float) 1.3);
                insatisfecho.setScaleX((float) 1.3);
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setRepeatCount(0);
                insatisfecho.playAnimation();
                calificacionrealizada=true;
                calificacion=calificacion_uno;

                break;

            case "Muy Insatisfecho":
                muy_insatisfecho.setAnimation("check_animation.json");
                muy_insatisfecho.setRepeatCount(0);
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
        satisfecho = (LottieAnimationView) findViewById(R.id.satisfecho_dos);
        muy_satisfecho = (LottieAnimationView) findViewById(R.id.muy_satisfecho_dos);
        insatisfecho = (LottieAnimationView) findViewById(R.id.Insatisfecho_dos);
        muy_insatisfecho = (LottieAnimationView) findViewById(R.id.muy_insatisfecho_dos);
        adelante = (LottieAnimationView) findViewById(R.id.adelante);
        atras = (LottieAnimationView) findViewById(R.id.atras);

        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calificacionrealizada){
                    Intent next = new Intent(DosActivity.this,TresActivity.class);
                    next.putExtra("servicio",servicio);
                    next.putExtra("calificacion_uno",calificacion_uno);
                    next.putExtra("calificacion_dos",calificacion);
                    next.putExtra("calificacion_tres",calificacion_tres);
                    next.putExtra("calificacion_cuatro",calificacion_cuatro);
                    next.putExtra("calificacion_cinco",calificacion_cinco);
                    next.putExtra("relacion_universidad",relacion);
                    startActivity(next);finish();

                }else{
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.pregunta_no_calificada, (ViewGroup) findViewById(R.id.pregunta_no_calificada));
                    TextView texto = (TextView) view.findViewById(R.id.texto_no_calificada);
                    texto.setText("Usted no ha calificado esta pregunta");
                    Toast t = new Toast(getApplicationContext());
                    t.setDuration(Toast.LENGTH_SHORT);
                    t.setView(view);
                    t.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                    t.show();
                }
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(DosActivity.this,UnoActivity.class);
                next.putExtra("servicio",servicio);
                next.putExtra("calificacion_uno",calificacion_uno);
                next.putExtra("calificacion_dos",calificacion);
                next.putExtra("calificacion_tres",calificacion_tres);
                next.putExtra("calificacion_cuatro",calificacion_cuatro);
                next.putExtra("calificacion_cinco",calificacion_cinco);
                next.putExtra("relacion_universidad",relacion);
                startActivity(next);
                finish();
            }
        });


        satisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("check_animation.json");
                satisfecho.setRepeatCount(0);
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.setRepeatCount(20000);
                muy_satisfecho.playAnimation();

                insatisfecho.setRepeatCount(0);
                insatisfecho.cancelAnimation();
                insatisfecho.setAnimation("decline_arrows.json");
                //insatisfecho.playAnimation();
                insatisfecho.setPadding(0,40,0,10);
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
                insatisfecho.playAnimation();

                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.setRepeatCount(20000);
                muy_insatisfecho.playAnimation();
                progress=40;
                calificacion="Satisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


        muy_satisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                satisfecho.setAnimation("happy.json");
                satisfecho.setRepeatCount(20000);
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("check_animation.json");
                muy_satisfecho.setRepeatCount(0);
                muy_satisfecho.playAnimation();

                insatisfecho.setRepeatCount(0);
                insatisfecho.cancelAnimation();
                insatisfecho.setAnimation("decline_arrows.json");
                //insatisfecho.playAnimation();
                insatisfecho.setPadding(0,40,0,10);
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
                insatisfecho.playAnimation();


                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.setRepeatCount(20000);
                muy_insatisfecho.playAnimation();
                progress=40;
                calificacion="Muy Satisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


        insatisfecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                satisfecho.setAnimation("happy.json");
                satisfecho.setRepeatCount(20000);
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.setRepeatCount(20000);
                muy_satisfecho.playAnimation();


                insatisfecho.setPadding(0,0,0,20);
                insatisfecho.setScaleY((float) 1.3);
                insatisfecho.setScaleX((float) 1.3);
                insatisfecho.setAnimation("check_animation.json");
                insatisfecho.setRepeatCount(0);
                insatisfecho.playAnimation();


                muy_insatisfecho.setAnimation("angry_emoji.json");
                muy_insatisfecho.setRepeatCount(20000);
                muy_insatisfecho.playAnimation();
                progress=40;
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
                insatisfecho.setPadding(0,40,0,10);
                insatisfecho.setScaleY((float) 4.8);
                insatisfecho.setScaleX((float) 4.8);
                //insatisfecho.cancelAnimation();
                insatisfecho.setRepeatCount(20000);
                insatisfecho.setAnimation("not_found.json");
                //insatisfecho.resumeAnimation();
                insatisfecho.playAnimation();


                satisfecho.setAnimation("happy.json");
                satisfecho.setRepeatCount(20000);
                satisfecho.playAnimation();
                muy_satisfecho.setAnimation("laugh.json");
                muy_satisfecho.setRepeatCount(20000);
                muy_satisfecho.playAnimation();


                muy_insatisfecho.setScaleX(1);
                muy_insatisfecho.setScaleY(1);
                //muy_insatisfecho.setPadding(0,100,0,5);
                muy_insatisfecho.setAnimation("check_animation.json");
                muy_insatisfecho.setRepeatCount(0);
                muy_insatisfecho.playAnimation();


                progress=40;
                calificacion="Muy Insatisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


    }
}
