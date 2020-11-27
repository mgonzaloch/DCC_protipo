package com.example.dcc_protipo.fragmentos.models;

public class modelp {
    String cate,empr,foto,nomb;
    Double prec;

    public modelp() {

    }
    public modelp(String cate, String desc, String empr, String foto, String nomb, Boolean disp, Boolean envi, Double prec) {
        this.cate = cate;

        this.empr = empr;
        this.foto = foto;
        this.nomb = nomb;

        this.prec = prec;
    }
    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }



    public String getEmpr() {
        return empr;
    }

    public void setEmpr(String empr) {
        this.empr = empr;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }



    public Double getPrec() {
        return prec;
    }

    public void setPrec(Double prec) {
        this.prec = prec;
    }

}
