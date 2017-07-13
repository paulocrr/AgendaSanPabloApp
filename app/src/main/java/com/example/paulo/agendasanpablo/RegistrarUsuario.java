package com.example.paulo.agendasanpablo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Vector;

public class RegistrarUsuario extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText usuario;
    EditText password;
    Button registrar;
    wsAccess ws;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        setTitle("Nuevo Usuario");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        registrar = (Button) findViewById(R.id.btn_registrar);
        String[] Carreras = {"Ciencias de la Computación", "Ingeniería Civil", "Ingeniería Electroníca y Telecomunicaciones", "Ingenieria Industrial","Psicología","Administración de Negocios","Contabilidad","Educación","Derecho"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Carreras);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.spinner_carreras);
        materialDesignSpinner.setAdapter(arrayAdapter);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ws = new wsAccess();
                prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Vector<String> respuesta = new Vector<String>();
                nombre = (EditText) findViewById(R.id.input_nombre);
                apellido = (EditText) findViewById(R.id.input_apellido);
                correo = (EditText) findViewById(R.id.input_correo);
                usuario = (EditText) findViewById(R.id.input_usuario);
                password = (EditText) findViewById(R.id.input_password);
                respuesta = ws.registrarUsuario(nombre.getText().toString(),apellido.getText().toString(),correo.getText().toString(),usuario.getText().toString(),password.getText().toString());
                if(respuesta.get(0).equals("0")){
                    Snackbar.make(v, "Error al Registrarse", Snackbar.LENGTH_LONG)
                            .show();
                }else {
                    editor.putString("idUsuario",respuesta.get(0));
                    editor.putString("nombre",nombre.getText().toString());
                    editor.putString("apellido",apellido.getText().toString());
                    Intent i = new Intent(RegistrarUsuario.this,HomeUsuario.class);
                    startActivity(i);
                }
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
