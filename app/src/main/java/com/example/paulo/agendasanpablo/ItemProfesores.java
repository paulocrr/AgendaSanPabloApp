package com.example.paulo.agendasanpablo;

/**
 * Created by paulo on 7/13/2017.
 */

public class ItemProfesores {
    public int id;
    public String nombre;
    public String puntaje;
    public String votos;
    ItemProfesores(int id,String nombre,String puntaje,String votos){
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.votos = votos;
    }
}
