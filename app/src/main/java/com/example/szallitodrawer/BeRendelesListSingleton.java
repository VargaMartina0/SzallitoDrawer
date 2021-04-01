package com.example.szallitodrawer;

import java.util.ArrayList;
import java.util.List;

public class BeRendelesListSingleton {
    private ArrayList<Rendeles> rendelesList;
    private BeRendelesListSingleton(){
        rendelesList = new ArrayList<>();
    }
    public ArrayList<Rendeles> getRendelesList(){
        return rendelesList;
    }
    private static BeRendelesListSingleton beRendelesek;
    public static BeRendelesListSingleton get(){
        if(beRendelesek == null){
            beRendelesek = new BeRendelesListSingleton();
        }
        return beRendelesek;
    }
    /*public Rendeles getRendeles(int position){
        return getRendelesList().get(position);
    }*/
    public void addRendeles(Rendeles rendeles){
        rendelesList.add(rendeles);
    }
}