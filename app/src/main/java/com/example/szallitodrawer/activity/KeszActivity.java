package com.example.szallitodrawer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.adapter.KeszRecyclerAdapter;
import com.example.szallitodrawer.fragment.BeerkezettRendelesekFragment;
import com.example.szallitodrawer.fragment.KeszRendelesekFragment;
import com.example.szallitodrawer.fragment.UjRendelesFragment;
import com.example.szallitodrawer.services.BeRendelesService;
import com.example.szallitodrawer.services.KeszRendelesService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
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
                        .addToBackStack("Matykó")
                        .commit();

            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameKesz, new KeszRendelesekFragment())
                .commit();
        /*Button algButton = findViewById(R.id.algoritmus);
        *//*if(BeRendelesService.getInstance().getRendelesList().size()<10){
            algButton.setEnabled(false);
        }else algButton.setEnabled(true);*//*
        algButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int size = KeszRendelesService.getInstance().getKeszRendelesList().size();
                double[][] locations = new double[size][2];
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<size; i++ ) {
                    String address = KeszRendelesService.getInstance().getKeszRendelesList().get(i).getCim();
//                    GeoLocation geoLocation = new GeoLocation();

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



                double[][] distances = getDistances(locations, size);
                sb.append(distances[0][1]);
                Toast toast = Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG);
                toast.show();


            }
        });*/
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

    public Context getContext(){
        return this;
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
}
