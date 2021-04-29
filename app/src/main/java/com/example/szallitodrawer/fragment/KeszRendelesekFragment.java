package com.example.szallitodrawer.fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.activity.KeszActivity;
import com.example.szallitodrawer.adapter.BeerkezettRecyclerAdapter;
import com.example.szallitodrawer.adapter.KeszRecyclerAdapter;
import com.example.szallitodrawer.data.OnClickListener;
import com.example.szallitodrawer.services.BeRendelesService;
import com.example.szallitodrawer.services.KeszRendelesService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class KeszRendelesekFragment extends Fragment implements OnClickListener {

    private KeszRecyclerAdapter recyclerAdapter;

    private FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (isVisible()) {
                recyclerAdapter.setKeszRendelesList(KeszRendelesService.getInstance().getKeszRendelesList());
            }
        }
    };

    public KeszRecyclerAdapter getRecyclerAdapter(){
        return recyclerAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kesz_rendelesek, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewKesz);
        recyclerAdapter = new KeszRecyclerAdapter();
        recyclerAdapter.setOnClickListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        /*
        AVOID USING APPLICATION CONTEXT reference when possible
        it can cause problems!
         */
        //getApplicationContext()

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setKeszRendelesList(KeszRendelesService.getInstance().getKeszRendelesList());

        getFragmentManager().addOnBackStackChangedListener(listener);


    }

    @Override
    public void removeRendeles(int i){
        recyclerAdapter.keszRendelesList.remove(i);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentManager().removeOnBackStackChangedListener(listener);
    }

}
