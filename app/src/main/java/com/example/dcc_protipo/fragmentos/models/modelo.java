package com.example.dcc_protipo.fragmentos.models;

public class modelo
{
    String nomb,opin;

    modelo()
    {

    }

    public modelo(String nomb, String opin) {
        this.nomb = nomb;
        this.opin = opin;
    }


    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getOpin() {
        return opin;
    }

    public void setOpin(String opin) {
        this.opin = opin;
    }


}
