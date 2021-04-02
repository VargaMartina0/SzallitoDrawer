package com.example.szallitodrawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.data.Rendeles;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    /**
     * use of list as type declaration is preferred
     * ArrayList is an implementation, List is an abstraction
     */
    private List<Rendeles> rendelesList;

    /**
     * a better way to handle incoming data is to add setters for them
     * <p>
     * notifyDataSetChanged call is required for the recyclerView to update
     */
    public void setRendelesList(List<Rendeles> rendelesList) {
        this.rendelesList = rendelesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        you inflated R.layout.activity_beerkezett here
        this is VERY WRONG
        that layout belongs to the activity, this causes a circular referencing throughout the application
        use list_item_ layouts (practical)
         */

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_beerkezett, parent, false);
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
        holder.bind(this.rendelesList.get(position));
    }

    @Override
    public int getItemCount() {
        return rendelesList == null ? 0 : rendelesList.size();
    }

    /**
     * when it is possible, use static internal classes
     * they are preferred as the object does not rely on outer object instance
     * <p>
     * internal classes look nicer if they are first or last in containing class
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nevText;
        private final TextView cimText;
        private final TextView telefonszamText;

        public MyViewHolder(final View view) {
            super(view);
            nevText = view.findViewById(R.id.nev);
            cimText = view.findViewById(R.id.cim);
            telefonszamText = view.findViewById(R.id.telefonszam);

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
