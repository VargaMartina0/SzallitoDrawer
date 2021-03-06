package com.example.szallitodrawer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.adapter.KeszRecyclerAdapter;
import com.example.szallitodrawer.data.Rendeles;
import com.example.szallitodrawer.fragment.KeszRendelesekFragment;
import com.example.szallitodrawer.fragment.SzallitasFragment;
import com.example.szallitodrawer.fragment.UjRendelesFragment;
import com.example.szallitodrawer.services.KeszRendelesService;
import com.example.szallitodrawer.services.SzallitasRendelesService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class KeszActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesz);

        drawerLayout = findViewById(R.id.drawer_layout);

        FloatingActionButton fab = findViewById(R.id.fabKesz);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.drawer_layout, new UjRendelesFragment()).commit();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameKesz, new UjRendelesFragment())
                        .addToBackStack("Matyk??")
                        .commit();

            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameKesz, new KeszRendelesekFragment())
                .commit();

        final Button algButton = findViewById(R.id.algoritmus);
        /*if(BeRendelesService.getInstance().getRendelesList().size()<10){
            algButton.setEnabled(false);
        }else algButton.setEnabled(true);*/
        algButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //TODO: ki kell t??r??lni a szallitasListb??l a rendel??seket -> implements OnClickListener kell
                int size = KeszRendelesService.getInstance().getKeszRendelesList().size() + 1;
                Rendeles etterem = new Rendeles("??tterem", "Esztergom Dorogi ??t 5", "");
                //KeszRendelesService.getInstance().getKeszRendelesList().add(etterem);
                List<Rendeles> algList = new ArrayList<>();
                algList.add(etterem);
                algList.addAll(KeszRendelesService.getInstance().getKeszRendelesList());
                double[][] locations = new double[size][2];
                for(int i=0; i<size; i++ ) {
                    //String address = KeszRendelesService.getInstance().getKeszRendelesList().get(i).getCim();
                    String address = algList.get(i).getCim();

                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try{
                        List addressList = geocoder.getFromLocationName(address,1);
                        if(addressList!= null && addressList.size()>0){
                            Address addressA = (Address) addressList.get(0);
                            locations[i][0] = addressA.getLatitude();
                            locations[i][1] = addressA.getLongitude();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



                double[][] distances =getDistances(locations, size);

                double[][] times = new double[size][size];
                for(int j=0; j<size; j++){
                    for(int k=0; k<size; k++){

                        if(j==k){
                            times[j][k] = 0;
                        }else times[j][k] = distances[j][k]/40000*60;
                        distances[j][k] = distances[j][k]/1000;
                    }
                }
                //distance -> km-ben
                //times -> percben
                //hatralevoIdo -> percben


                int[] hatralevoIdo = new int[size];
                LocalTime ido;
                long timeDifference;
                for(int j=0; j<size; j++){
                    ido = LocalTime.now();
                    LocalTime l1 = algList.get(j).getLocalTime();//KeszRendelesService.getInstance().getKeszRendelesList().get(j).getLocalTime();
                    timeDifference = Duration.between(l1, ido).toMinutes();
                    hatralevoIdo[j] = 80 - (int) timeDifference;
                }
                hatralevoIdo[0] = 0;
                int[] indexes = algoritmus(distances,times,hatralevoIdo);
                //TODO: indexekb??l meg kell mondani hogy melyikek a t??r??lhet?? rendel??sek
                int[] torolhetoRendelesek;//TODO: ezt m??g inicializ??lni kell, ez int[]?

                //tfh az utols?? az etterem, sz??val azt kihagyjuk, ??gy csak a lenght-1-ig megy??nk
                int number;
                Rendeles rendeles;
                for(int j=0; j<indexes.length-1; j++){//TODO: itt is majd az indexes size-a kell
                    number = indexes[j];
                    rendeles = algList.get(number);//KeszRendelesService.getInstance().getKeszRendelesList().get(number);
                    SzallitasRendelesService.getInstance().addSzallithatoRendeles(rendeles);
                }//kiveszem a kesz list??b??l ami kell ??s berakom a sz??ll??that??k k??z??

                List<Integer> indexesList = new ArrayList<>();// = new ArrayList(Arrays.asList(indexes));
                for(int j=0; j<indexes.length; j++){
                    indexesList.add(indexes[j]);
                }
                Collections.sort(indexesList);
                Collections.reverse(indexesList);
                /*StringBuilder sb = new StringBuilder();

                Toast toast = new Toast(getApplicationContext());
                toast.makeText(getApplicationContext(), sb, Toast.LENGTH_LONG).show();*/

                for(int j=0; j<size-1; j++){//TODO: most az eg??szet t??rli, de ha megvannak a torolhetoRendelesek, akkor majd itt annak a m??ret??ig kell menni
                    System.out.println(KeszRendelesService.getInstance().getKeszRendelesList().get(0).getNev());
                    System.out.println(KeszRendelesService.getInstance().getKeszRendelesList().size());
                    KeszRecyclerAdapter.listener.removeRendeles(0);

                }


                //TODO: ki kell t??r??lni a KeszRendelesServiceb??l az ??ttermet
                //KeszRecyclerAdapter.listener.removeRendeles(0);

                /*getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameKesz, new SzallitasFragment())
                        .addToBackStack("Hi")
                        .commit();*/
                DialogFragment newFragment = SzallitasFragment.newInstance();
                newFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
            }
        });

    }

    public double[][] getDistances(double[][] locations, int size) {
        double[][] distances = new double[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                Location loc1 = new Location("");
                loc1.setLatitude(locations[i][0]);
                loc1.setLongitude(locations[i][1]);

                Location loc2 = new Location("");
                loc2.setLatitude(locations[j][0]);
                loc2.setLongitude(locations[j][1]);

                double distanceInMeters = loc1.distanceTo(loc2);
                distances[i][j] = distanceInMeters;
            }
        }
        return distances;
    }

    public void ClickMenu(View view){
        BeerkezettActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        BeerkezettActivity.closeDrawer(drawerLayout);
    }

    public void ClickBeerkezett(View view){
        BeerkezettActivity.redirectActivity(this, BeerkezettActivity.class);
    }
    public void ClickKesz(View view){
        recreate();
    }

    public void ClickFutarok(View view){
        BeerkezettActivity.redirectActivity(this, FutarokActivity.class);
    }
    public void ClickKilep(View view){
        BeerkezettActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BeerkezettActivity.closeDrawer(drawerLayout);
    }

    private int[] algoritmus(double[][] distances, double[][] times, int[] hatralevoIdo){
        int[] indexes = new int[hatralevoIdo.length];
        for(int i =0; i<hatralevoIdo.length; i++){//TODO: ezt most nyilv??n csak felt??lt??m az ??sszes index-szel
            indexes[i] = i;
        }
        indexes[0] = indexes.length-1;
        indexes[indexes.length-1] = 0;
        return indexes;
    }
}