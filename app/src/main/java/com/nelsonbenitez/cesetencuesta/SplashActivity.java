package com.nelsonbenitez.cesetencuesta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {


private ImageView egresado,estudiante,docente,empleado, entidades,visitante,proveedor;
private String relacion_universidad;
ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);


        egresado = (ImageView) findViewById(R.id.egresado);
        estudiante = (ImageView) findViewById(R.id.estudiante);
        docente = (ImageView) findViewById(R.id.docente);
        empleado = (ImageView) findViewById(R.id.empleado);
        entidades = (ImageView) findViewById(R.id.entidades);
        visitante = (ImageView) findViewById(R.id.visitante);
        proveedor = (ImageView) findViewById(R.id.proveedor);



        egresado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Egresado";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });

        estudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Estudiante";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });

        docente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Docente";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });


        empleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Empleado";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });

        entidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Entidades";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });

        visitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Visitante";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });


        proveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relacion_universidad="Proveedor";
                goToProgress();
                goToMain(relacion_universidad);
            }
        });

    }

    private void goToProgress() {
        progreso = new ProgressDialog(SplashActivity.this);
        progreso.setMessage("Cargando Unidades a Evaluar ");
        progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progreso.setIndeterminate(true);
        progreso.show();
    }

    private void goToMain(String relacion_universidad) {

        Intent uno = new Intent().setClass(SplashActivity.this,MainActivity.class);
        uno.putExtra("servicio","servicio");
        uno.putExtra("calificacion_uno","uno");
        uno.putExtra("calificacion_dos","dos");
        uno.putExtra("calificacion_tres","tres");
        uno.putExtra("calificacion_cuatro","cuatro");
        uno.putExtra("calificacion_cinco","cinco");
        uno.putExtra("relacion_universidad", this.relacion_universidad);
        startActivity(uno);
        progreso.dismiss();

    }
}
