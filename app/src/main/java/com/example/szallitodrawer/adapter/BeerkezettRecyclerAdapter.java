package com.example.szallitodrawer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.data.Rendeles;
import com.example.szallitodrawer.services.BeRendelesService;
import com.example.szallitodrawer.services.KeszRendelesService;

import java.util.ArrayList;
import java.util.List;

public class BeerkezettRecyclerAdapter extends RecyclerView.Adapter<BeerkezettRecyclerAdapter.BeerkezettMyViewHolder> {

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
    public BeerkezettRecyclerAdapter.BeerkezettMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
        you inflated R.layout.activity_beerkezett here
        this is VERY WRONG
        that layout belongs to the activity, this causes a circular referencing throughout the application
        use list_item_ layouts (practical)
         */

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_beerkezett, parent, false);
        return new BeerkezettMyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerkezettRecyclerAdapter.BeerkezettMyViewHolder holder, int position) {
        /*String nev = BeRendelesListSingleton.getRendelesList(position).getNev();
        holder.nevText.setText(nev);
        String cim = rendelesList.get(position).getCim();
        holder.cimText.setText(cim);
        String telefonszam = rendelesList.get(position).getTelefonszam();
        holder.telefonszamText.setText(telefonszam);*/
        holder.beBind(this.rendelesList.get(position));
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
    public class BeerkezettMyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nevText;
        private final TextView cimText;
        private final TextView telefonszamText;
        private final Button torles;
        private final CheckBox checkBox;

        public BeerkezettMyViewHolder(final View view) {
            super(view);
            nevText = view.findViewById(R.id.nev);
            cimText = view.findViewById(R.id.cim);
            telefonszamText = view.findViewById(R.id.telefonszam);
            torles = view.findViewById(R.id.torlesButton);
            torles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = getAdapterPosition();
                    removeRendeles(id);
                }
            });
            checkBox = view.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Is the view now checked?
                    boolean checked = ((CheckBox) view).isChecked();
                    int number = getAdapterPosition();

                    switch(view.getId()) {
                        case R.id.checkBox:
                            if (checked){
                                String nev = BeRendelesService.getInstance().getRendelesList().get(number).getNev();
                                String cim = BeRendelesService.getInstance().getRendelesList().get(number).getCim();
                                String telefonszam = BeRendelesService.getInstance().getRendelesList().get(number).getTelefonszam();
                                KeszRendelesService.getInstance().addRendeles(new Rendeles(nev,cim,telefonszam));
                                removeRendeles(number);
                            }
                    }
                }
            });
            

        }

        /**
         * don't pass position to the bound layout
         */
        public void beBind(Rendeles rendeles) {
            nevText.setText(rendeles.getNev());
            cimText.setText(rendeles.getCim());
            telefonszamText.setText(rendeles.getTelefonszam());

            /*
            current position is adapter can be retrieved
             */
            int position = getAdapterPosition();
        }

    }
    public void removeRendeles(int id){
        rendelesList.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id, rendelesList.size());
    }
}
