package com.example.paulo.agendasanpablo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class RegistrarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        setTitle("Nuevo Usuario");
        String[] Carreras = {"Ciencias de la Computación", "Ingeniería Civil", "Ingeniería Electroníca y Telecomunicaciones", "Ingenieria Industrial","Psicología","Administración de Negocios","Contabilidad","Educación","Derecho"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Carreras);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.spinner_carreras);
        materialDesignSpinner.setAdapter(arrayAdapter);
    }
}
