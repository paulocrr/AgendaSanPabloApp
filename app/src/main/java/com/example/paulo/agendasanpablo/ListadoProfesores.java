package com.example.paulo.agendasanpablo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListadoProfesores extends AppCompatActivity {
    wsAccess ws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_profesores);
        setTitle("Listado de Profesores");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ws = new wsAccess();
        List<ItemProfesores> data = new ArrayList<>();
        String resutlado = ws.listadoProfesores();
        if (resutlado.equals("0")){
            Toast.makeText(this,"No Hay profesores que listar",Toast.LENGTH_SHORT).show();
        }else{
            try {
                JSONArray jsonObj = new JSONArray(resutlado);
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject jsonobject = jsonObj.getJSONObject(i);
                    int id = jsonobject.getInt("id");
                    String name = jsonobject.getString("nombre");
                    String apellido = jsonobject.getString("apellido");
                    name = "Profesor: " + name + " " +apellido;
                    String puntaje = "Puntaje: "+jsonobject.getString("nota_final");
                    String votos = "Numero de Votos: "+jsonobject.getString("num_votos");
                    data.add(new ItemProfesores(id,name,puntaje,votos));
                }
                RecyclerView rv = (RecyclerView) findViewById(R.id.listaProfesores);
                RecyclerViewProfesores adapter = new RecyclerViewProfesores(data,getApplicationContext());
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeUsuario.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
