package com.tutorials.hp.mdlistviewediting.mData;

public class Usuario{

    public String nombre,email,actividades;

    public Usuario() {
    }

    public Usuario(String nombre, String email, String actividades) {
        this.nombre = nombre;
        this.email = email;
        this.actividades = actividades;
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

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }
}