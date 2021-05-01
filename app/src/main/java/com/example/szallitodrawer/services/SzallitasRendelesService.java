package com.example.szallitodrawer.services;

import com.example.szallitodrawer.data.Rendeles;

import java.util.ArrayList;
import java.util.List;

public class SzallitasRendelesService {

    private static final Object mutex = new Object();

    private static SzallitasRendelesService instance;

    public static SzallitasRendelesService getInstance(){
        if(instance == null){
            synchronized (mutex){
                if(instance == null){
                    instance = new SzallitasRendelesService();
                }
            }
        }
        return instance;
    }

    private final List<Rendeles> szallitasList = new ArrayList<>();

    private SzallitasRendelesService(){

    }

    public List<Rendeles> getSzallitasRendelesList(){
        return szallitasList;
    }

    public void addSzallithatoRendeles(Rendeles rendeles){
        szallitasList.add(rendeles);
    }
}
