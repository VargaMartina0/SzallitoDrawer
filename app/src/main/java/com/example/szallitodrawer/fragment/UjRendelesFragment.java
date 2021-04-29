package com.example.szallitodrawer.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.szallitodrawer.services.BeRendelesService;
import com.example.szallitodrawer.R;
import com.example.szallitodrawer.data.Rendeles;

/**
 * A simple {@link Fragment} subclass.
 */
public class UjRendelesFragment extends Fragment {

    private EditText nev_editText;
    private EditText cim_editText;
    private EditText telefonszam_editText;
    private Button keszButton;
    public UjRendelesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uj_rendeles, container, false);
    }

    @Override //ezen belülre kell írni a findViewById-kat
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        keszButton = view.findViewById(R.id.fragment_button);
        nev_editText = view.findViewById(R.id.editNev);
        cim_editText = view.findViewById(R.id.editCim);
        telefonszam_editText = view.findViewById(R.id.editTelefonszam);
        keszButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cim = cim_editText.getText().toString();
                String nev = nev_editText.getText().toString();
                String telefonszam = telefonszam_editText.getText().toString();
                BeRendelesService.getInstance().addRendeles(new Rendeles(nev,cim,telefonszam));
                getFragmentManager().popBackStack();

                nev_editText.setText("");
                cim_editText.setText("");
                telefonszam_editText.setText("");
            }
        });


    }
}
//dataService -> Singleton private constructor statikus változó
//rendelesList-et kéne lecserélni, hogy a dataService menjenek az adatok
//BeerkezettRendelesekFragment Fragment betölt

