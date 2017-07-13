package com.example.paulo.agendasanpablo;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by paulo on 7/13/2017.
 */

public class View_Holder_Profesores extends RecyclerView.ViewHolder {
    CardView cv;
    TextView NombreProfesor;
    TextView PuntajeProfesor;
    TextView VotosProfesor;
    public View_Holder_Profesores(final View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.itemProfesor);
        NombreProfesor = (TextView) itemView.findViewById(R.id.txtProfesor);
        PuntajeProfesor = (TextView) itemView.findViewById(R.id.txtPuntaje);
        VotosProfesor = (TextView) itemView.findViewById(R.id.txtVotos);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(itemView.getContext(),CalificarProfesores.class);
                i.putExtra("CodProfesor",NombreProfesor.getTag().toString());
                i.putExtra("NomProfesor",NombreProfesor.getText().toString());
                itemView.getContext().startActivity(i);
            }
        });
    }
}
