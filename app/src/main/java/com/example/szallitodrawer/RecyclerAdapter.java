package com.example.szallitodrawer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Rendeles> rendelesList;

    public RecyclerAdapter(ArrayList<Rendeles> rendelesList){
        super();
        this.rendelesList = rendelesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nevText;
        private TextView cimText;
        private TextView telefonszamText;
        int position;

        public MyViewHolder(final View view){
            super(view);
            nevText =  view.findViewById(R.id.nev);
            cimText =  view.findViewById(R.id.cim);
            telefonszamText = view.findViewById(R.id.telefonszam);

        }

        public void bind(Rendeles rendeles, int pos){
            position = pos;
            nevText.setText(rendeles.getNev());
            cimText.setText(rendeles.getCim());
            telefonszamText.setText(rendeles.getTelefonszam());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_beerkezett, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        /*String nev = BeRendelesListSingleton.getRendelesList(position).getNev();
        holder.nevText.setText(nev);
        String cim = rendelesList.get(position).getCim();
        holder.cimText.setText(cim);
        String telefonszam = rendelesList.get(position).getTelefonszam();
        holder.telefonszamText.setText(telefonszam);*/
        holder.bind(this.rendelesList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return rendelesList.size();
    }
}
