package com.moviles.workinonit;

import java.util.ArrayList;

/**
 * Created by arroy_000 on 11/12/2016.
 */
public class Userss {
    private String nombre;
    private String email;
    private ArrayList<Activity> lista;

    public Userss() {
    }

    public Userss(String nombre, String email, ArrayList<Activity> lista) {
        this.nombre = nombre;
        this.email = email;
        this.lista = lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Activity> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Activity> lista) {
        this.lista = lista;
    }
}
