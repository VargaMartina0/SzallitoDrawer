package com.example.szallitodrawer.services;

import com.example.szallitodrawer.data.Rendeles;

import java.util.ArrayList;
import java.util.List;

public class BeRendelesService {

    /**
     * thread synchronization object
     */
    private static final Object mutex = new Object();

    /**
     * don't mix static and non-static functions and variables
     * as they are called differently
     * <p>
     * use singleton naming
     * -> instance
     * -> getInstance
     */
    private static BeRendelesService instance;

    /**
     * a preferred getInstance call :)
     */
    public static BeRendelesService getInstance() {
        if (instance == null) {
            // synchronized block means no multi threaded access is possible
            // all the calling threads occurring in the same time will wait for each other
            synchronized (mutex) {
                // as other threads are waiting, it is possible the instance is there
                if (instance == null) {
                    // create the instance when it is really null
                    instance = new BeRendelesService();
                }
            }
        }
        return instance;
    }

    /**
     * use empty spaces between lines
     */

    private final List<Rendeles> rendelesList = new ArrayList<>();


    private BeRendelesService() {
        List<Rendeles> kezdetiList = new ArrayList<>();
        kezdetiList.add(new Rendeles("Kiss János", "Esztergom, Magyary László utca 2.", "301234567"));
        kezdetiList.add(new Rendeles("Kovács Márta", "Esztergom, Kenderesi út 17.", "302334567"));
        kezdetiList.add(new Rendeles("Nagy Ferenc", "Esztergom, Mátyás király utca 28.", "303434567"));
        kezdetiList.add(new Rendeles("Szűcs Béláné", "Esztergom, Temesvári utca 12.", "304534567"));
        kezdetiList.add(new Rendeles("Szegedi Gergő", "Esztergom, Dobó István utca 39.", "305634567"));
        kezdetiList.add(new Rendeles("Muzslai Gergő", "Esztergom, Petz testvérek utca 18.", "306734567"));
        kezdetiList.add(new Rendeles("Keil Anna", "Esztergom, Jókai Mór utca 9.", "307834567"));
        kezdetiList.add(new Rendeles("Zempléni Orsolya", "Esztergom, Hunyadi János utca 16.", "308934567"));
        kezdetiList.add(new Rendeles("Harman Roland", "Esztergom, Lázár Vilmos utca 15.", "309034567"));
        kezdetiList.add(new Rendeles("Sárdi Réka", "Esztergom, Aulich Lajos utca 13.", "01301334567"));
        kezdetiList.add(new Rendeles("Bélai Bálint", "Esztergom, Blaha Lujza utca 4.", "301434567"));
        kezdetiList.add(new Rendeles("Kovács Eszter", "Esztergom, Homok utca 3.", "301534567"));
        kezdetiList.add(new Rendeles("Juhász Gábor", "Esztergom, Eperjesi út 97.", "301634567"));
        kezdetiList.add(new Rendeles("Emmer Roland", "Esztergom, Budai Nagy Antal utca 8.", "301734567"));
        kezdetiList.add(new Rendeles("József Zoltán", "Esztergom, Gizella királyné utca 7.", "301834567"));
        kezdetiList.add(new Rendeles("Vörösmarty Mihály", "Esztergom, Garam utca 11.", "301934567"));
        kezdetiList.add(new Rendeles("Petőfi Sándor", "Esztergom, Bánomi lakótelep 12.", "301034567"));
        kezdetiList.add(new Rendeles("Koronás András", "Esztergom, Gyurgyalag utca 4.", "301244567"));
        kezdetiList.add(new Rendeles("Csere Petra", "Esztergom, Csengele utca 2.", "301254567"));
        kezdetiList.add(new Rendeles("Katona Dávid", "Esztergom, Esze Tamás utca 5.", "301264567"));
        kezdetiList.add(new Rendeles("Horváth György Márk", "Esztergom, Aranyeső utca 2.", "301274567"));
        kezdetiList.add(new Rendeles("Lőrincz Péter", "Esztergom, Aranyhegyi út 11.", "301284567"));
        kezdetiList.add(new Rendeles("Miskei István", "Esztergom, Kolozsvári utca 56.", "301294567"));
        kezdetiList.add(new Rendeles("Csillag Patrik", "Esztergom, Simor János utca 19.", "301204567"));
        kezdetiList.add(new Rendeles("Spongya Róbert", "Esztergom, Szalézi út 8.", "301235567"));
        kezdetiList.add(new Rendeles("Hosszú Katinka", "Esztergom, Pozsonyi utca 23.", "301236567"));
        kezdetiList.add(new Rendeles("Verrasztó Evelin", "Esztergom, Hőtáv utca 10.", "301237567"));
        kezdetiList.add(new Rendeles("Igaly Diána", "Esztergom, Irinyi János utca 24.", "301239567"));
        kezdetiList.add(new Rendeles("Jónás György", "Esztergom, Határ utca 6.", "301230567"));
        kezdetiList.add(new Rendeles("Kelemen Jácint", "Esztergom, Töltés utca 4.", "301234569"));

        rendelesList.addAll(kezdetiList);
    }

    public List<Rendeles> getRendelesList() {
        return rendelesList;
    }

    /*public Rendeles getRendeles(int position){
        return getRendelesList().get(position);
    }*/
    public void addRendeles(Rendeles rendeles) {
        rendelesList.add(rendeles);
    }
}