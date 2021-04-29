package com.example.szallitodrawer;

import java.time.LocalTime;
import java.util.Date;

public class Rendeles {

    private String nev;
    private String cim;
    private String telefonszam;
    private LocalTime localTime;

    public Rendeles(String nev, String cim, String telefonszam) {
        this.nev = nev;
        this.cim = cim;
        this.telefonszam = telefonszam;
        this.localTime = LocalTime.now();
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

    public LocalTime getLocalTime(){return localTime;}
}
