package com.example.szallitodrawer.adapter;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.activity.KeszActivity;
import com.example.szallitodrawer.data.Rendeles;
import com.example.szallitodrawer.fragment.KeszRendelesekFragment;
import com.example.szallitodrawer.services.KeszRendelesService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class KeszRecyclerAdapter extends RecyclerView.Adapter<KeszRecyclerAdapter.MyViewHolder> {

    /**
     * use of list as type declaration is preferred
     * ArrayList is an implementation, List is an abstraction
     */
    public List<Rendeles> keszRendelesList;
    private Context context;


    /**
     * a better way to handle incoming data is to add setters for them
     * <p>
     * notifyDataSetChanged call is required for the recyclerView to update
     */
    public void setKeszRendelesList(List<Rendeles> keszRendelesList) {
        this.keszRendelesList = keszRendelesList;
        notifyDataSetChanged();
    }

    public KeszRecyclerAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public KeszRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        you inflated R.layout.activity_beerkezett here
        this is VERY WRONG
        that layout belongs to the activity, this causes a circular referencing throughout the application
        use list_item_ layouts (practical)
         */


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kesz, parent, false);
        return new KeszRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KeszRecyclerAdapter.MyViewHolder holder, int position) {
        /*String nev = BeRendelesListSingleton.getRendelesList(position).getNev();
        holder.nevText.setText(nev);
        String cim = rendelesList.get(position).getCim();
        holder.cimText.setText(cim);
        String telefonszam = rendelesList.get(position).getTelefonszam();
        holder.telefonszamText.setText(telefonszam);*/
        holder.bind(this.keszRendelesList.get(position));
    }

    @Override
    public int getItemCount() {
        return keszRendelesList == null ? 0 : keszRendelesList.size();
    }

    /**
     * when it is possible, use static internal classes
     * they are preferred as the object does not rely on outer object instance
     * <p>
     * internal classes look nicer if they are first or last in containing class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nevText;
        private final TextView cimText;
        private final TextView telefonszamText;
        private final Button torles;
        //private final Button algButton;


        public MyViewHolder(final View view) {
            super(view);
            nevText = view.findViewById(R.id.keszNev);
            cimText = view.findViewById(R.id.keszCim);
            telefonszamText = view.findViewById(R.id.keszTelefonszam);
            torles = view.findViewById(R.id.keszTorlesButton);
            torles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = getAdapterPosition();
                    keszRendelesList.remove(id);
                    notifyDataSetChanged();
                }
            });
            /*algButton = view.findViewById(R.id.algoritmus);
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

                        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
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
                    Toast toast = Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG);
                    toast.show();

                    for(int i=0; i<size; i++){
                        keszRendelesList.remove(i);
                        notifyDataSetChanged();
                    }


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



        /**
         * don't pass position to the bound layout
         */
        public void bind(Rendeles rendeles) {
            nevText.setText(rendeles.getNev());
            cimText.setText(rendeles.getCim());
            telefonszamText.setText(rendeles.getTelefonszam());

            /*
            current position is adapter can be retrieved
             */
            int position = getAdapterPosition();
        }
    }

}
