package com.nelsonbenitez.cesetencuesta;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import de.hdodenhof.circleimageview.CircleImageView;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;


public class CincoActivity extends AppCompatActivity {


    String relacion,servicio, calificacion_uno, calificacion_dos,calificacion_tres,calificacion_cuatro, calificacion_cinco, calificacion;
    LottieAnimationView satisfecho, insatisfecho,muy_insatisfecho;
    LottieAnimationView muy_satisfecho, atras, adelante;
    CircleImageView service;
    String textoSugerencias;
    ProgressDialog progreso;

    RingProgressBar estadoEncuenta;
    private int progress=90;
    boolean calificacionrealizada=false;


    ProgressDialog progressDialog;


   // public static final String URL="https://docs.google.com/forms/d/e/1FAIpQLSeADliK-dcWROd-9hbrD2yi4sTY4N8b6mgx00_1Xczb6QUw-g/formResponse";
    //public static final String SERVICIO_Y_RELACION="entry.948104490";
    //public static final String CALIFICACION_UNO_DOS="entry.1253399098";
    //public static final String CALIFICACIONES_TRES_CUATRO_CINCO="entry.1222907485";
    //public static final String SUGERENCIAS="entry.1594495333";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinco);



        Intent informacion= getIntent();
        servicio=informacion.getStringExtra("servicio");
        calificacion_uno=informacion.getStringExtra("calificacion_uno");
        calificacion_dos=informacion.getStringExtra("calificacion_dos");
        calificacion_tres=informacion.getStringExtra("calificacion_tres");
        calificacion_cuatro=informacion.getStringExtra("calificacion_cuatro");
        calificacion_cinco=informacion.getStringExtra("calificacion_cinco");
        relacion=informacion.getStringExtra("relacion_universidad");

        service = (CircleImageView) findViewById(R.id.service);
        identificaServicioEvaluado(service,servicio);

        //Toast.makeText(this, "Recibí de Cuatro"+calificacion_cinco, Toast.LENGTH_SHORT).show();

        estadoEncuenta= (RingProgressBar) findViewById(R.id.progress_bar_5) ;
        estadoEncuenta.setProgress(progress);

        addListenerOnButton();
        seleccionarOpcion(calificacion_cinco);

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
        satisfecho = (LottieAnimationView) findViewById(R.id.satisfechoCinco);
        muy_satisfecho = (LottieAnimationView) findViewById(R.id.muy_satisfechoCinco);
        insatisfecho = (LottieAnimationView) findViewById(R.id.InsatisfechoCinco);
        muy_insatisfecho = (LottieAnimationView) findViewById(R.id.muy_insatisfechoCinco);
        adelante = (LottieAnimationView) findViewById(R.id.adelante);
        atras = (LottieAnimationView) findViewById(R.id.atras);

        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (calificacionrealizada) {
                    final AlertDialog.Builder sugerencia = new AlertDialog.Builder(CincoActivity.this);
                    View sugerenciaView = getLayoutInflater().inflate(R.layout.sugerencia, null);

                    final EditText sugerencias = (EditText) sugerenciaView.findViewById(R.id.texto_sugerencias);
                    final Button btnContinuar = (Button) sugerenciaView.findViewById(R.id.btn_continuar);

                    btnContinuar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textoSugerencias = sugerencias.getText().toString();



                            AlertDialog.Builder alerta = new AlertDialog.Builder(CincoActivity.this);
                            View mView = getLayoutInflater().inflate(R.layout.send_data_verification,null);
                            LottieAnimationView uno = (LottieAnimationView) mView.findViewById(R.id.califica_one);
                            LottieAnimationView two = (LottieAnimationView) mView.findViewById(R.id.califica_two);
                            LottieAnimationView three = (LottieAnimationView) mView.findViewById(R.id.califica_three);
                            LottieAnimationView four = (LottieAnimationView) mView.findViewById(R.id.califica_four);
                            LottieAnimationView five = (LottieAnimationView) mView.findViewById(R.id.califica_five);
                            CircleImageView servicio_eval = (CircleImageView) mView.findViewById(R.id.servicio_evaluado);
                            identificaServicioEvaluado(servicio_eval,servicio);
                            calificaSend(calificacion_uno,uno);
                            calificaSend(calificacion_dos,two);
                            calificaSend(calificacion_tres,three);
                            calificaSend(calificacion_cuatro,four);
                            calificaSend(calificacion,five);


                            final Button btnSi = (Button) mView.findViewById(R.id.btn_si);
                            final Button btnNo = (Button) mView.findViewById(R.id.btn_no);

                            btnNo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    gotoActivity(CincoActivity.this,CincoActivity.class);
                                    calificacionrealizada=true;
                                    progress=100;
                                    finish();
                                }
                            });


                            btnSi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    progreso = new ProgressDialog(CincoActivity.this);
                                    progreso.setMessage("Se está enviando su calificación. ");
                                    progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                    progreso.setIndeterminate(true);
                                    progreso.show();

                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzYToU64ZgG85Md15QFHj6tSJjHK0bmD27mKArjGJlDiiGYn1g/exec",
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    if (response.equals("Success")) {
                                                        progreso.dismiss();
                                                        Intent successs = new Intent(CincoActivity.this, ExitoActivity.class);
                                                        startActivity(successs);
                                                        finish();
                                                    }



                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    LayoutInflater inflater = getLayoutInflater();
                                                    View view = inflater.inflate(R.layout.pregunta_no_calificada, (ViewGroup) findViewById(R.id.pregunta_no_calificada));
                                                    TextView texto = (TextView) view.findViewById(R.id.texto_no_calificada);
                                                    texto.setText("No se pudo enviar la calificación.\nRevise la Conexión a Internet");
                                                    Toast t = new Toast(getApplicationContext());
                                                    t.setDuration(Toast.LENGTH_LONG);
                                                    t.setView(view);
                                                    t.setGravity(Gravity.CENTER,0,0);
                                                    t.show();
                                                    gotoActivity(CincoActivity.this,CincoActivity.class);
                                                }
                                            }
                                    ) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> parmas = new HashMap<>();

                                            //here we pass params
                                            parmas.put("action","addItem");
                                            parmas.put("servicio",servicio);
                                            parmas.put("relacion_universidad",relacion);
                                            parmas.put("pregunta_uno",calificacion_uno);
                                            parmas.put("pregunta_dos",calificacion_dos);
                                            parmas.put("pregunta_tres",calificacion_tres);
                                            parmas.put("pregunta_cuatro",calificacion_cuatro);
                                            parmas.put("pregunta_cinco",calificacion);
                                            parmas.put("sugerencias",textoSugerencias);


                                            return parmas;
                                        }
                                    };

                                    int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

                                    RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                                    stringRequest.setRetryPolicy(retryPolicy);

                                    RequestQueue queue = Volley.newRequestQueue(CincoActivity.this);

                                    queue.add(stringRequest);



                                }
                            });


                            alerta.setView(mView);
                            AlertDialog dialog = alerta.create();
                            dialog.show();
                        }
                    });

                    sugerencia.setView(sugerenciaView);
                    AlertDialog dialogSugerencia = sugerencia.create();
                    dialogSugerencia.show();

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
                Intent next = new Intent(CincoActivity.this,CuatroActivity.class);
                next.putExtra("servicio",servicio);
                next.putExtra("calificacion_uno",calificacion_uno);
                next.putExtra("calificacion_dos",calificacion_dos);
                next.putExtra("calificacion_tres",calificacion_tres);
                next.putExtra("calificacion_cuatro",calificacion_cuatro);
                next.putExtra("calificacion_cinco",calificacion);
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
                progress=100;
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
                progress=100;
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
                progress=100;
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


                progress=100;
                calificacion="Muy Insatisfecho";
                calificacionrealizada=true;
                estadoEncuenta.setProgress(progress);
            }
        });


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

    private void calificaSend(String calificacion_uno, LottieAnimationView uno) {

        if (calificacion_uno.equals("Muy Satisfecho")){
            uno.setAnimation("laugh.json");
            uno.playAnimation();
        }
        else if (calificacion_uno.equals("Satisfecho")){
            uno.setAnimation("happy.json");
            uno.playAnimation();
        }
        else if (calificacion_uno.equals("Insatisfecho")){
            uno.setPadding(0,40,0,5);
            uno.setScaleY((float) 2.2);
            uno.setScaleX((float) 2.2);
            uno.setAnimation("not_found.json");
            uno.playAnimation();
        }
        else if (calificacion_uno.equals("Muy Insatisfecho")){
            uno.setScaleY((float) 0.9);
            uno.setScaleX((float) 0.9);
            uno.setAnimation("angry_emoji.json");
            uno.playAnimation();
        }
    }




    private void gotoActivity(CincoActivity cincoActivity, Class<CincoActivity> cincoActivityClass) {

        Intent next = new Intent(cincoActivity,cincoActivityClass);
        next.putExtra("servicio",servicio);
        next.putExtra("calificacion_uno",calificacion_uno);
        next.putExtra("calificacion_dos",calificacion_dos);
        next.putExtra("calificacion_tres",calificacion_tres);
        next.putExtra("calificacion_cuatro",calificacion_cuatro);
        next.putExtra("calificacion_cinco",calificacion);
        next.putExtra("relacion_universidad",relacion);
        startActivity(next);
        finish();
    }


}
