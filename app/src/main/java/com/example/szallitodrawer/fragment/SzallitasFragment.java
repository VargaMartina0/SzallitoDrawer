package com.example.szallitodrawer.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.adapter.SzallitasRecyclerAdapter;
import com.example.szallitodrawer.services.KeszRendelesService;
import com.example.szallitodrawer.services.SzallitasRendelesService;

public class SzallitasFragment extends DialogFragment {

    private SzallitasRecyclerAdapter szallitasRecyclerAdapter;

    private FragmentManager.OnBackStackChangedListener listener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (isVisible()) {
                szallitasRecyclerAdapter.setSzallitasRendelesList(SzallitasRendelesService.getInstance().getSzallitasRendelesList());
            }
        }
    };

    public static SzallitasFragment newInstance() {
        SzallitasFragment f = new SzallitasFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_szallitas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSzallitas);
        szallitasRecyclerAdapter = new SzallitasRecyclerAdapter();
        //szallitasRecyclerAdapter.setSzallitasOnClickListener(listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(szallitasRecyclerAdapter);
        szallitasRecyclerAdapter.setSzallitasRendelesList(SzallitasRendelesService.getInstance().getSzallitasRendelesList());

        getFragmentManager().addOnBackStackChangedListener(listener);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        getFragmentManager().removeOnBackStackChangedListener(listener);
    }
}
