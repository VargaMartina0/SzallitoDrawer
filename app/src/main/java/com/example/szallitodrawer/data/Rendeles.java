package com.example.szallitodrawer.data;

public class Rendeles {

    private String nev;
    private String cim;
    private String telefonszam;

    public Rendeles(String nev, String cim, String telefonszam) {
        this.nev = nev;
        this.cim = cim;
        this.telefonszam = telefonszam;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getNev() {
        return nev;
    }

    public String getCim() {
        return cim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }
}
