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