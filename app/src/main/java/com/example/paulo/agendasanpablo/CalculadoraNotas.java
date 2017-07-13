package com.example.paulo.agendasanpablo;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculadoraNotas extends AppCompatActivity {
    public static ArrayList<String> num = new ArrayList<String>();
    public static ArrayList<String> porcent = new ArrayList<String>();
    Button limpiar;
    double sum,comprobar, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_notas);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final EditText editTextNum = (EditText) findViewById(R.id.editTextNum);
        final EditText editTextPorcent = (EditText) findViewById(R.id.editText3);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonCalc = (Button) findViewById(R.id.buttonCalc);
        limpiar = (Button) findViewById(R.id.btnLimpiar);
        final TextView textViewRes = (TextView) findViewById(R.id.textViewRes);
        final TextView suggest = (TextView) findViewById(R.id.textViewSuggest);

        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                textViewRes.setText(" ");
                String numCatch = editTextNum.getText().toString().trim();
                String porcentCatch = editTextPorcent .getText().toString().trim();

                if ((numCatch.length() != 0) && (porcentCatch.length() != 0) && Float.parseFloat(porcentCatch)<=100 && Float.parseFloat(numCatch)<=20) {
                    num.add(numCatch);
                    porcent.add(porcentCatch);
                    editTextNum.setText("");
                    editTextPorcent.setText("");
                    suggest.append("\n Nota : " + numCatch + "  Porcentaje : " + porcentCatch + "%" );
                }
                else {
                    textViewRes.setText("Intenta denuevo");
                }
            }
        });

        buttonCalc.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View arg0) {
                DecimalFormat format = new DecimalFormat("#.##");
                DecimalFormat formatSug = new DecimalFormat("#.##");
                for (int i = 0; i < porcent.size(); i++) {
                    comprobar = Float.parseFloat(porcent.get(i)) + comprobar;
                }

                if(comprobar>100){
                    textViewRes.setText("Porcentajes incorrectos "+ comprobar + "%");
                    suggest.setText("");

                }

                else {

                    for (int i = 0; i < num.size(); i++) {
                        sum = Float.parseFloat(num.get(i)) * (Float.parseFloat(porcent.get(i))) + sum;
                    }
                    res = sum / 100;

                    if(res<11.5 && res>0) {
                        suggest.setText("Sugerencia: \n Te falta " + formatSug.format((11.5 - res)) + " para aprobar!");
                        if(comprobar<100){
                            suggest.append("\n Aun te queda "+(100-(int)comprobar)+"% que aprovechar , no te rindas!");
                        }
                        else{
                            suggest.append("\n:(");
                        }

                    }
                    if(res>11.5){
                        suggest.append("\nTienes " + res + "!");
                    }

                    textViewRes.setText("Tu acumulado es " + format.format(res));
                    res = 0;
                    sum = 0;


                }
                comprobar = 0;
                num.clear();
                porcent.clear();
            }

        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.clear();
                porcent.clear();
                suggest.setText("");
                textViewRes.setText("");
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeUsuario.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
