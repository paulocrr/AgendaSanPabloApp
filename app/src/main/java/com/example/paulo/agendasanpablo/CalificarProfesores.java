package com.example.paulo.agendasanpablo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CalificarProfesores extends AppCompatActivity {
    wsAccess ws;
    Button Calificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_profesores);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final String idProfesor = intent.getStringExtra("CodProfesor");
        String NombreProfesor = intent.getStringExtra("NomProfesor");
        setTitle(NombreProfesor);
        ws = new wsAccess();
        Calificar = (Button) findViewById(R.id.btn_calificar);
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        final String resultado = ws.listadoCriterios();
        if (resultado.equals("0")){
            Toast.makeText(this,"No hay Criterios para Mostrar",Toast.LENGTH_SHORT).show();
        }else{
            try {
                LinearLayout layout=(LinearLayout) findViewById(R.id.linearLayout);
                JSONArray jsonObj = new JSONArray(resultado);
                for (int i = 0; i < jsonObj.length(); i++) {
                    JSONObject jsonobject = jsonObj.getJSONObject(i);
                    int id = jsonobject.getInt("id");
                    String name = jsonobject.getString("nombre");
                    Spinner spinner = new Spinner(this);
                    TextView msg = new TextView(this);
                    msg.setText(name);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setId(id);
                    spinner.setAdapter(spinnerArrayAdapter);
                    layout.addView(msg);
                    layout.addView(spinner);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Calificar.setOnClickListener(new View.OnClickListener() {
            Vector<String> res = new Vector<String>();
            @Override
            public void onClick(View v) {
                try {
                    JSONArray jsonObj = new JSONArray(resultado);
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject jsonobject = jsonObj.getJSONObject(i);
                        int id = jsonobject.getInt("id");
                        Spinner sp = (Spinner) findViewById(id);
                        res = ws.calificarProfesor(sp.getSelectedItem().toString(),idProfesor,String.valueOf(id));
                        if(res.get(0).equals("0")){
                            Log.d("Spinner", "onClick: " + sp.getSelectedItem());
                        }else{
                            Log.d("Spinner", "No Funciona");
                        }

                    }
                    Toast.makeText(CalificarProfesores.this, "Se califico al Profesor", Snackbar.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(CalificarProfesores.this,HomeUsuario.class);
                    startActivity(i);

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
