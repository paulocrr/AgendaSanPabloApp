package com.example.paulo.agendasanpablo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    EditText usuario;
    EditText password;
    Button Ingresar;
    Button Registrar;
    wsAccess ws;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Registrar = (Button) findViewById(R.id.btn_registrar);
        Ingresar = (Button) findViewById(R.id.btn_ingresar);
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Vector<String> respuesta = new Vector<String>();
                usuario = (EditText)findViewById(R.id.input_usuario);
                password = (EditText)findViewById(R.id.input_password);
                ws = new wsAccess();
                respuesta = ws.validarUsuario(usuario.getText().toString(),password.getText().toString());
                if(respuesta.get(0).equals("0")){
                    Snackbar.make(v, "El usuario o password es Incorrecto", Snackbar.LENGTH_LONG).show();
                }else {
                    editor.putString("idUsuario",respuesta.get(0));
                    editor.putString("nombre",respuesta.get(1));
                    editor.putString("apellido",respuesta.get(2));
                    editor.commit();
                    Intent i = new Intent(MainActivity.this,HomeUsuario.class);
                    startActivity(i);
                }
            }
        });
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegistrarUsuario.class);
                startActivity(i);
            }
        });
    }
}
