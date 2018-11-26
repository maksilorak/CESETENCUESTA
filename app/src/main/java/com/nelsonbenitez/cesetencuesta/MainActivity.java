package com.nelsonbenitez.cesetencuesta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView egresados, continua,mercadeo,emprendimiento,asesoria,practicas,iso, logistica,atencion_usuario;
    private String servicio,servicio_recibido,califica_uno,califica_dos,califica_tres,califica_cuatro,califica_cinco,relacion;

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
        relacion=informacion.getStringExtra("relacion_universidad");


        egresados = (CircleImageView) findViewById(R.id.egresados);
        continua = (CircleImageView) findViewById(R.id.continua);
        mercadeo = (CircleImageView) findViewById(R.id.mercadeo);
        emprendimiento = (CircleImageView) findViewById(R.id.emprendimiento);
        asesoria = (CircleImageView) findViewById(R.id.asesoria);
        practicas = (CircleImageView) findViewById(R.id.practicas);
        logistica = (CircleImageView) findViewById(R.id.logistic);
        atencion_usuario = (CircleImageView) findViewById(R.id.user);
        iso = (CircleImageView) findViewById(R.id.grupoiso);


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

        logistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Logística";
                validaServicio();
            }
        });


        atencion_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio="Atención al Usuario";
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
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_same_unit,null);
            TextView mensaje = (TextView) mView.findViewById(R.id.servico_en_evaluacion);
            mensaje.setText("Usted ya estaba evaluando "+ servicio+".");
            //TextView mensajeDos =(TextView) mView.findViewById(R.id.texto_dos_en_eval);
            Button btnSi = (Button) mView.findViewById(R.id.btn_si);
            Button btnNo = (Button) mView.findViewById(R.id.btn_no);

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent stay = new Intent(MainActivity.this,MainActivity.class);
                    stay.putExtra("servicio",servicio);
                    stay.putExtra("calificacion_uno",califica_uno);
                    stay.putExtra("calificacion_dos",califica_dos);
                    stay.putExtra("calificacion_tres",califica_tres);
                    stay.putExtra("calificacion_cuatro",califica_cuatro);
                    stay.putExtra("calificacion_cinco",califica_cinco);
                    stay.putExtra("relacion_universidad",relacion);
                    startActivity(stay);
                }
            });

            btnSi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivity();
                }
            });



            alerta.setView(mView);
            AlertDialog dialog = alerta.create();
            dialog.show();



        }else{

            if (servicio_recibido.equals("servicio"))
            {
                califica_uno=" ";
                califica_dos=" ";
                califica_tres=" ";
                califica_cuatro=" ";
                califica_cinco=" ";
                goToActivity();
            }else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_same_unit,null);
                TextView mensaje = (TextView) mView.findViewById(R.id.servico_en_evaluacion);
                TextView mensajeDos =(TextView) mView.findViewById(R.id.texto_dos_en_eval);
                mensaje.setText("Usted estaba evaluando " + servicio_recibido + " anteriormente y acaba de seleccionar "+servicio+".");
                mensajeDos.setText("\nSe perderán las respuestas anteriores.\n\n¿Desea Continuar?");
                Button btnSi = (Button) mView.findViewById(R.id.btn_si);
                Button btnNo = (Button) mView.findViewById(R.id.btn_no);


                btnSi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        califica_uno=" ";
                        califica_dos=" ";
                        califica_tres=" ";
                        califica_cuatro=" ";
                        califica_cinco=" ";
                        goToActivity();
                    }
                });


                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent stay = new Intent(MainActivity.this, MainActivity.class);
                        stay.putExtra("servicio", servicio_recibido);
                        stay.putExtra("calificacion_uno", califica_uno);
                        stay.putExtra("calificacion_dos", califica_dos);
                        stay.putExtra("calificacion_tres", califica_tres);
                        stay.putExtra("calificacion_cuatro", califica_cuatro);
                        stay.putExtra("calificacion_cinco", califica_cinco);
                        stay.putExtra("relacion_universidad",relacion);
                        startActivity(stay);
                    }
                });

                alerta.setView(mView);
                AlertDialog dialog = alerta.create();
                dialog.show();
            }

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
        informacion.putExtra("relacion_universidad",relacion);
        startActivity(informacion);
        finish();

    }
}
