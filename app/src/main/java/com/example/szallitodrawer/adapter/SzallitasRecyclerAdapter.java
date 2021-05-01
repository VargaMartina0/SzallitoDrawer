package com.example.szallitodrawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.data.OnClickListener;
import com.example.szallitodrawer.data.Rendeles;

import java.util.List;

public class SzallitasRecyclerAdapter extends RecyclerView.Adapter<SzallitasRecyclerAdapter.SzallitasMyViewHolder>{

    private List<Rendeles> szallitasList;
    public static OnClickListener szallitasListener;

    public void setSzallitasRendelesList(List<Rendeles> szallitasList){
        this.szallitasList = szallitasList;
        notifyDataSetChanged();
    }

    public void setSzallitasOnClickListener(OnClickListener listener){
        this.szallitasListener = listener;
    }

    @NonNull
    @Override
    public SzallitasRecyclerAdapter.SzallitasMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_szallitas, parent, false);
        return new SzallitasMyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SzallitasRecyclerAdapter.SzallitasMyViewHolder holder, int position){
        holder.beBind(this.szallitasList.get(position));
    }

    @Override
    public int getItemCount(){
        return szallitasList == null ? 0 : szallitasList.size();
    }

    public class SzallitasMyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nevText;
        private final TextView cimText;
        private final TextView telefonText;

        public SzallitasMyViewHolder(final View view){
            super(view);
            nevText = view.findViewById(R.id.szallitasNev);
            cimText = view.findViewById(R.id.szallitasCim);
            telefonText = view.findViewById(R.id.szallitasTelefonszam);
        }

        public void beBind(Rendeles rendeles){
            nevText.setText(rendeles.getNev());
            cimText.setText(rendeles.getCim());
            telefonText.setText(rendeles.getTelefonszam());
        }
    }



}
