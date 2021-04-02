package com.example.szallitodrawer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.services.BeRendelesService;
import com.example.szallitodrawer.R;
import com.example.szallitodrawer.adapter.RecyclerAdapter;

public class BeerkezettRendelesekFragment extends Fragment {

    private RecyclerAdapter recyclerAdapter;

    private FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (isVisible()) {
                recyclerAdapter.setRendelesList(BeRendelesService.getInstance().getRendelesList());
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beerkezett_rendelesek, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBeerkezett);

        recyclerAdapter = new RecyclerAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        /*
        AVOID USING APPLICATION CONTEXT reference when possible
        it can cause problems!
         */
        //getApplicationContext()
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setRendelesList(BeRendelesService.getInstance().getRendelesList());

        getFragmentManager().addOnBackStackChangedListener(listener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentManager().removeOnBackStackChangedListener(listener);
    }
}
