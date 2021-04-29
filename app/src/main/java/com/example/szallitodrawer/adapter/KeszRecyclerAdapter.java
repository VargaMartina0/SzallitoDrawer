package com.example.szallitodrawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.data.OnClickListener;
import com.example.szallitodrawer.data.Rendeles;

import java.util.List;

public class KeszRecyclerAdapter extends RecyclerView.Adapter<KeszRecyclerAdapter.MyViewHolder> {

    /**
     * use of list as type declaration is preferred
     * ArrayList is an implementation, List is an abstraction
     */
    public List<Rendeles> keszRendelesList;
    public static OnClickListener listener;


    /**
     * a better way to handle incoming data is to add setters for them
     * <p>
     * notifyDataSetChanged call is required for the recyclerView to update
     */
    public void setKeszRendelesList(List<Rendeles> keszRendelesList) {
        this.keszRendelesList = keszRendelesList;
        notifyDataSetChanged();
    }
    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
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
    public void onBindViewHolder(@NonNull KeszRecyclerAdapter.MyViewHolder holder, final int position) {

        /*String nev = BeRendelesListSingleton.getRendelesList(position).getNev();
        holder.nevText.setText(nev);
        String cim = rendelesList.get(position).getCim();
        holder.cimText.setText(cim);
        String telefonszam = rendelesList.get(position).getTelefonszam();
        holder.telefonszamText.setText(telefonszam);*/

        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeRendeles(position);
            }
        };
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
        }

        /**
         * don't pass position to the bound layout
         */
        public void bind (Rendeles rendeles){
            nevText.setText(rendeles.getNev());
            cimText.setText(rendeles.getCim());
            telefonszamText.setText(rendeles.getTelefonszam());
        }
    }
}
