package com.example.paulo.agendasanpablo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class VerHorarios extends AppCompatActivity {
    Spinner dias;
    TextView msgDia;
    Button getHorario;
    wsAccess ws;
    SharedPreferences prefs;
    ListView verHorario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_horarios);
        prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        ws = new wsAccess();
        final Vector<String> idDias = new Vector<String>();
        final Vector<String> vectorDias = new Vector<String>();
        final Vector<String> horarioDia = new Vector<String>();
        dias = (Spinner) findViewById(R.id.spinner_dias);
        getHorario = (Button) findViewById(R.id.btn_buscar_horario);
        verHorario = (ListView) findViewById(R.id.listaHorario);
        msgDia = (TextView) findViewById(R.id.msgDia);
        setTitle("Horario");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String resutlado = ws.getDias();
        Log.d("Dia", "onCreate: "+resutlado);
        if (resutlado.equals("0")){
            Toast.makeText(this,"No Hay profesores que listar",Toast.LENGTH_SHORT).show();
        }else{
            try {
                JSONArray jsonObj = new JSONArray(resutlado);
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject jsonobject = jsonObj.getJSONObject(i);
                    int id = jsonobject.getInt("id");
                    idDias.add(String.valueOf(id));
                    String dia = jsonobject.getString("dia");
                    vectorDias.add(dia);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VerHorarios.this
                        ,android.R.layout.simple_spinner_item,vectorDias);
                dias.setAdapter(arrayAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        getHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horarioDia.removeAllElements();
                String nDia = dias.getSelectedItem().toString();
                String id = idDias.get(vectorDias.indexOf(nDia));
                String idUser = prefs.getString("idUsuario","");
                String json =ws.getHorariosDia(id,idUser);
                Log.d("Dia", "onCreate: "+idUser);
                msgDia.setText("Horario Dia "+nDia);
                Toast.makeText(VerHorarios.this,"Click",Toast.LENGTH_SHORT);
                try {
                    JSONArray jsonObj = new JSONArray(json);
                    if (jsonObj.length()>0){
                        for (int i = 0; i < jsonObj.length(); i++) {
                            JSONObject jsonobject = jsonObj.getJSONObject(i);
                            String horario = jsonobject.getString("nombre") + " - Hora Inicio: "+jsonobject.getString("hora_inicio") + " - Hora Fin: "+jsonobject.getString("hora_fin");
                            horarioDia.add(horario);
                        }
                    }else{
                        horarioDia.add("Genial Tienes el Dia Libre");
                    }

                    ArrayAdapter<String> arrayAdapterHorario = new ArrayAdapter<String>(VerHorarios.this,android.R.layout.simple_list_item_1,horarioDia);
                    verHorario.setAdapter(arrayAdapterHorario);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeUsuario.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
