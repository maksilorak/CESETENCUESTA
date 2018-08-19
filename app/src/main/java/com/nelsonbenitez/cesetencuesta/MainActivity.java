package com.nelsonbenitez.cesetencuesta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView egresados, continua,mercadeo,emprendimiento,asesoria,practicas,iso;
    private String servicio,servicio_recibido,califica_uno,califica_dos,califica_tres,califica_cuatro,califica_cinco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se recibe la info cuando se regresa desde la calificacion de la primera pregunta

        Intent informacion= getIntent();
        servicio_recibido=informacion.getStringExtra("servicio");
        califica_uno=informacion.getStringExtra("calificacion_uno");
        califica_dos=informacion.getStringExtra("calificacion_dos");
        califica_tres=informacion.getStringExtra("calificacion_tres");
        califica_cuatro=informacion.getStringExtra("calificacion_cuatro");
        califica_cinco=informacion.getStringExtra("calificacion_cinco");


        egresados = (CircleImageView) findViewById(R.id.egresados);
        continua = (CircleImageView) findViewById(R.id.continua);
        mercadeo = (CircleImageView) findViewById(R.id.mercadeo);
        emprendimiento = (CircleImageView) findViewById(R.id.emprendimiento);
        asesoria = (CircleImageView) findViewById(R.id.asesoria);
        practicas = (CircleImageView) findViewById(R.id.practicas);
        iso = (CircleImageView) findViewById(R.id.iso);

        egresados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Egresados";
                validaServicio();

            }
        });

        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Educación Continua";
                validaServicio();
            }
        });

        mercadeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Mercadeo y Comunicaciones";
                validaServicio();
            }
        });

        emprendimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Emprendimiento e Innovación";
                validaServicio();
            }
        });

        asesoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Asesoría y Consultoría";
                validaServicio();
            }
        });

        practicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Prácticas Académicas";
                validaServicio();
            }
        });

        iso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Grupo ISO";
                validaServicio();
            }
        });


    }


    private void validaServicio() {

        if (servicio.equals(servicio_recibido)){
            //Toast.makeText(this, "Seleccionó el mismo servico que estaba evaluando anteriormente.  Puede modificar sus respuestas", Toast.LENGTH_LONG).show();
            final AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle("ALERTA");
            alerta.setMessage("Usted ya estaba evaluando "+ servicio+". ¿Desea seguir evaluando este servicio?");

            alerta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    goToActivity();
                }
            });


            alerta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent stay = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(stay);
                }
            });

            alerta.create();
            alerta.show();

        }else{
            califica_uno=" ";
            goToActivity();
        }

    }



    public void goToActivity(){
        android.content.Intent informacion = new android.content.Intent(MainActivity.this,UnoActivity.class);
        informacion.putExtra("servicio",servicio);
        informacion.putExtra("calificacion_uno",califica_uno);
        informacion.putExtra("calificacion_dos",califica_dos);
        informacion.putExtra("calificacion_tres",califica_tres);
        informacion.putExtra("calificacion_cuatro",califica_cuatro);
        informacion.putExtra("calificacion_cinco",califica_cinco);
        startActivity(informacion);
        finish();

    }
}
