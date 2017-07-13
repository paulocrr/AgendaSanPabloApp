package com.example.paulo.agendasanpablo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by paulo on 7/13/2017.
 */

public class RecyclerViewProfesores extends RecyclerView.Adapter<View_Holder_Profesores> {
    List<ItemProfesores> list = Collections.emptyList();
    Context context;
    private int lastPosition = -1;
    public RecyclerViewProfesores(List<ItemProfesores> list,Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public View_Holder_Profesores onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_profesores,parent,false);
        View_Holder_Profesores holder = new View_Holder_Profesores(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(View_Holder_Profesores holder, int position) {
        holder.NombreProfesor.setText(list.get(position).nombre);
        holder.NombreProfesor.setTag(list.get(position).id);
        holder.VotosProfesor.setText(list.get(position).votos);
        holder.VotosProfesor.setTag(list.get(position).id);
        holder.PuntajeProfesor.setText(list.get(position).puntaje);
        holder.PuntajeProfesor.setTag(list.get(position).id);
        setAnimation(holder.cv, position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
