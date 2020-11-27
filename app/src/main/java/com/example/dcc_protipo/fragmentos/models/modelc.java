package com.example.dcc_protipo.fragmentos.models;

public class modelc
{
    String nomb, dire, foto;

    modelc ()
    {

    }

    public modelc(String nombre, String direccion, String foto) {
        this.nomb = nombre;
        this.dire = direccion;
        this.foto = foto;
    }

    public String getNombre() {
        return nomb;
    }

    public void setNombre(String nombre) {
        this.nomb = nombre;
    }

    public String getDireccion() {
        return dire;
    }

    public void setDireccion(String direccion) {
        this.dire = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
